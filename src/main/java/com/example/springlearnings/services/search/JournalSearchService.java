package com.example.springlearnings.services.search;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.springlearnings.entity.Journal;
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
        SearchResponse<Journal> response = null;
        try {
            response = elasticsearchClient.search(s -> s
                            .index("journals")
                            .query(q -> q.matchAll(m -> m))
                            .size(100),
                    Journal.class
            );
        } catch (IOException e) {
            log.error("Error occurred while search.");
        }
        return Objects.isNull(response) ? Collections.emptyList() : response.hits().hits().stream().map(Hit::source).toList();
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
