package com.example.springlearnings.services.impl;

import com.example.springlearnings.api.models.JournalPayload;
import com.example.springlearnings.entity.Journal;
import com.example.springlearnings.entity.User;
import com.example.springlearnings.persistence.interfaces.IJournalRepository;
import com.example.springlearnings.persistence.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    private IUserRepository repository;
    @Autowired
    private IJournalRepository journalRepository;

    @Transactional
    public String createJournal(JournalPayload journalPayload, String username) {
        String id = UUID.randomUUID().toString();
        Journal journal = journalRepository.save(new Journal(id, journalPayload.getTitle(), journalPayload.getContent(), username));
        User user = repository.findByUsername(username);
        user.getJournalList().add(journal);
        repository.save(user);
        return journal.getId();
    }
}
