package com.example.springlearnings.services.search;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.example.springlearnings.entity.Journal;
import com.example.springlearnings.entity.JournalSearchDTO;
import com.example.springlearnings.entity.mappers.JournalAndJournalSearchDTOMapping;
import com.example.springlearnings.services.constants.Constants;
import com.example.springlearnings.services.interfaces.IJournalSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
public class JournalSearchService implements IJournalSearchService {
    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Override
    public List<Journal> searchJournals(String query, String username) {
        SearchResponse<JournalSearchDTO> response = null;
        try {
            response = elasticsearchClient.search(s -> s
                            .index(Constants.JOURNAL_INDEX)
                            .query(q -> q
                                    .bool(b -> b
                                            .must(m -> m.multiMatch(mm -> mm.query(query)
                                                    .fields("title", "content")))
                                            .filter(f -> f
                                                    .term(t -> t
                                                            .field("username.keyword")
                                                            .value(username)))))
                            .size(100),
                    JournalSearchDTO.class
            );
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return Objects.isNull(response)
                ? Collections.emptyList()
                : response.hits().hits().stream()
                .map(hit -> JournalAndJournalSearchDTOMapping
                        .toJournalEntity(hit.source())).toList();
    }

    @Override
    public void updateJournalsIndex(Journal journal) {
        try {
            elasticsearchClient.index(i -> i
                    .index("journals")
                    .id(journal.getId())
                    .document(journal)
            );
        } catch (IOException e) {
            log.error("Error occurred while updating journal index.");
        }

    }


}
