package com.example.springlearnings.services.interfaces;

import com.example.springlearnings.entity.Journal;

import java.util.List;

public interface IJournalSearchService {
    List<Journal> searchJournals(String query, String username);

    void updateJournalsIndex(Journal journal);
}
