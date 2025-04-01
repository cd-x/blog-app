package com.example.springlearnings.services.interfaces;

import com.example.springlearnings.entity.Journal;
import com.example.springlearnings.api.models.JournalPayload;
import com.example.springlearnings.services.errorhandling.exceptions.UserDoesNotExistException;

import java.util.List;

public interface IJournalManagementService {
    List<Journal> getJournals();
    Journal getJournalById(String id);
    String createJournal(JournalPayload payload) throws UserDoesNotExistException;
    void deleteJournal(String id);
    void updateJournal(String journalId, String username, String content);
}
