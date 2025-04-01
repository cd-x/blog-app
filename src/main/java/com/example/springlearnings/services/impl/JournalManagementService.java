package com.example.springlearnings.services.impl;

import com.example.springlearnings.api.models.JournalPayload;
import com.example.springlearnings.entity.Journal;
import com.example.springlearnings.persistence.IJournalRepository;
import com.example.springlearnings.services.errorhandling.exceptions.UserDoesNotExistException;
import com.example.springlearnings.services.interfaces.IJournalManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class JournalManagementService implements IJournalManagementService {
    @Autowired
    private TransactionService transactionService;
    
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
    public String createJournal(JournalPayload journalPayload) throws UserDoesNotExistException {
        try {
            String id = transactionService.createJournal(journalPayload);
            logger.debug("New journal created with id: {}", id);
            return id;
        } catch (RuntimeException runtimeException) {
            throw new UserDoesNotExistException();
        }
    }

    @Override
    public void deleteJournal(String id) {
        journalRepository.deleteById(id);
    }

    @Override
    public void updateJournal(String journalId, String username, String content) {
        Journal journal = journalRepository.findById(journalId).orElse(null);
        if (Objects.nonNull(journal)) {
            journal.setContent(content);
            journalRepository.save(journal);
        }
        logger.debug("journal with id:{} and username: {} updated.", journalId, username);
    }
}
