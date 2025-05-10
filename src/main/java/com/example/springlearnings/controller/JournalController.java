package com.example.springlearnings.controller;

import com.example.springlearnings.api.models.JournalPayload;
import com.example.springlearnings.entity.Journal;
import com.example.springlearnings.services.errorhandling.exceptions.UserDoesNotExistException;
import com.example.springlearnings.services.interfaces.IJournalManagementService;
import com.example.springlearnings.services.interfaces.IJournalSearchService;
import com.example.springlearnings.services.interfaces.IUserManagementService;
import com.example.springlearnings.utils.ControllerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/journal")
public class JournalController {
    @Autowired
    private IJournalManagementService journalManagementService;
    @Autowired
    private IUserManagementService userManagementService;
    @Autowired
    private IJournalSearchService journalSearchService;

    private final Logger LOGGER = LoggerFactory.getLogger(JournalController.class);


    @GetMapping(path = "/{id}")
    public ResponseEntity<Journal> getJournalById(@PathVariable String id) {
        if (validJournalId(id)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        try {
            Journal journal = journalManagementService.getJournalById(id);
            return new ResponseEntity<>(journal, HttpStatus.OK);
        } catch (NoSuchElementException noSuchElementException) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createJournal(@RequestBody JournalPayload payload) throws UserDoesNotExistException {
        LOGGER.debug("JournalController::createJournal with payload: {}", payload);
        String id = journalManagementService.createJournal(payload, ControllerUtils.getUsernameFromSecurityContext());
        Journal journal = journalManagementService.getJournalById(id);
        journalSearchService.updateJournalsIndex(journal);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> removeJournal(@PathVariable String id) {
        if (validJournalId(id)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        journalManagementService.deleteJournal(id);
        return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> updateJournal(@PathVariable String id, @RequestBody String content) {
        if (validJournalId(id)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        LOGGER.debug("JournalController::updateJournal with id: {}", id);
        journalManagementService.updateJournal(id, content);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    private boolean validJournalId(String id) {
        String username = ControllerUtils.getUsernameFromSecurityContext();
        return userManagementService.getUserByUserName(username)
                .getJournalList().stream().noneMatch(journal -> id.equalsIgnoreCase(journal.getId()));
    }

    @GetMapping(path = "/search")
    public List<Journal> searchJournals(@RequestParam String query) {
        return journalSearchService.searchJournals(query);
    }
}
