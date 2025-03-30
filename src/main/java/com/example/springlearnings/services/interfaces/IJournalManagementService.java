package com.example.springlearnings.services.interfaces;

import com.example.springlearnings.entity.Journal;
import com.example.springlearnings.api.models.JournalPayload;

import java.util.List;

public interface IJournalManagementService {
    List<Journal> getJournals();
    Journal getJournalById(String id);
    String createJournal(JournalPayload payload);
    void deleteJournal(String id);
    void updateJournal(String journalId, String username, String content);
}
