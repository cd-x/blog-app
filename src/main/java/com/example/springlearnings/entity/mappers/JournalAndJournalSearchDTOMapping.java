package com.example.springlearnings.entity.mappers;

import com.example.springlearnings.entity.Journal;
import com.example.springlearnings.entity.JournalSearchDTO;

public class JournalAndJournalSearchDTOMapping {
    public static JournalSearchDTO toJournalSearchDTO(Journal journal) {
        JournalSearchDTO journalSearchDTO = new JournalSearchDTO();
        journalSearchDTO.setId(journal.getId());
        journalSearchDTO.setTitle(journal.getTitle());
        journalSearchDTO.setContent(journal.getContent());
        journalSearchDTO.setUsername(journal.getUsername());
        return journalSearchDTO;
    }

    public static Journal toJournalEntity(JournalSearchDTO journalSearchDTO) {
        return new Journal(journalSearchDTO.getId(),
                journalSearchDTO.getTitle(),
                journalSearchDTO.getContent(),
                journalSearchDTO.getUsername());
    }
}
