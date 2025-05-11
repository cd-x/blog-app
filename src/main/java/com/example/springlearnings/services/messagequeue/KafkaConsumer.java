package com.example.springlearnings.services.messagequeue;

import com.example.springlearnings.entity.Journal;
import com.example.springlearnings.services.constants.Constants;
import com.example.springlearnings.services.interfaces.IJournalManagementService;
import com.example.springlearnings.services.interfaces.IJournalSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @Autowired
    private IJournalManagementService journalManagementService;
    @Autowired
    private IJournalSearchService journalSearchService;

    @KafkaListener(topics = Constants.JOURNAL_TOPIC, groupId = Constants.JOURNAL_GROUP)
    public void consume(String journalId) {
        Journal journal = journalManagementService.getJournalById(journalId);
        journalSearchService.updateJournalsIndex(journal);
    }
}
