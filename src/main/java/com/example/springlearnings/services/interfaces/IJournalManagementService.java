package com.example.springlearnings.services.interfaces;

import com.example.springlearnings.api.models.JournalPayload;
import com.example.springlearnings.entity.Journal;

import java.util.List;

public interface IJournalManagementService {
    List<Journal> getJournals();

    Journal getJournalById(String id);

    String createJournal(JournalPayload payload, String username);

    void deleteJournal(String id);

    void updateJournal(String journalId, String content);
}
