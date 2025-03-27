package com.example.springlearnings.services.impl;

import com.example.springlearnings.entity.Journal;
import com.example.springlearnings.api.models.JournalPayload;
import com.example.springlearnings.services.interfaces.IJournalManagementService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class JournalManagementService implements IJournalManagementService {
    private Map<String, Journal> journals  = new HashMap<>();
    @Override
    public List<Journal> getJournals() {
        return journals.values().stream().toList();
    }

    @Override
    public Journal getJournalById(String id) {
        return journals.get(id);
    }

    @Override
    public String createJournal(JournalPayload journalPayload) {
        String id = UUID.randomUUID().toString();
        journals.put(id, new Journal(id, journalPayload.getTitle(), journalPayload.getContent()));
        return id;
    }

    @Override
    public void deleteJournal(String id) {
        journals.remove(id);
    }

    @Override
    public void updateJournal(String id, String title, String content) {
        journals.put(id, new Journal(id, title, content));
    }
}
