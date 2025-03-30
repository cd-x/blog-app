package com.example.springlearnings.services.impl;

import com.example.springlearnings.entity.Journal;
import com.example.springlearnings.api.models.JournalPayload;
import com.example.springlearnings.persistence.IJournalRepository;
import com.example.springlearnings.services.interfaces.IJournalManagementService;
import com.example.springlearnings.services.interfaces.IUserManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JournalManagementService implements IJournalManagementService {
    @Autowired
    private IUserManagementService userManagementService;
    @Autowired
    private IJournalRepository journalRepository;
    private final Logger logger = LoggerFactory.getLogger(JournalManagementService.class);

    @Override
    public List<Journal> getJournals() {
        return journalRepository.findAll();
    }

    @Override
    public Journal getJournalById(String id) throws NoSuchElementException {
        return journalRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public String createJournal(JournalPayload journalPayload) {
        String id = UUID.randomUUID().toString();
        Journal journal = journalRepository.save(new Journal(id, journalPayload.getTitle(), journalPayload.getContent(), journalPayload.getUsername()));
        userManagementService.addJournalToList(journalPayload.getUsername(), journal);
        logger.debug("New journal created with id: {}", journal.getId());
        return journal.getId();
    }

    @Override
    public void deleteJournal(String id) {
        journalRepository.deleteById(id);
    }

    @Override
    public void updateJournal(String journalId, String username, String content) {
        journalRepository.updateContent(journalId, username, content);
        logger.debug("journal with id:{} and username: {} updated.", journalId, username);
    }
}
