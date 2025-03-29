package com.example.springlearnings.controller;

import com.example.springlearnings.entity.Journal;
import com.example.springlearnings.api.models.JournalPayload;
import com.example.springlearnings.services.interfaces.IJournalManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
    private Logger LOGGER = LoggerFactory.getLogger(JournalController.class);

    @GetMapping
    public ResponseEntity<List<Journal>> getJournals(){
        List<Journal> journalList = journalManagementService.getJournals();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-total-count", String.valueOf(journalList.size()));
        return new ResponseEntity<>(journalList, headers, HttpStatus.OK);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<Journal> getJournalById(@PathVariable String id){
        try {
            Journal journal = journalManagementService.getJournalById(id);
            return new ResponseEntity<>(journal, HttpStatus.OK);
        }catch (NoSuchElementException noSuchElementException){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<String> createJournal(@RequestBody JournalPayload payload){
        LOGGER.debug("JournalController::createJournal with payload: {}", payload);
        String id =  journalManagementService.createJournal(payload);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> removeJournal(@PathVariable String id){
        journalManagementService.deleteJournal(id);
        return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
    }
    @PutMapping
    public ResponseEntity<String> updateJournal(@RequestBody Journal payload){
        LOGGER.debug("JournalController::updateJournal with payload: {}", payload);
        journalManagementService.updateJournal(payload.getId(), payload.getTitle(), payload.getContent());
        return new ResponseEntity<>(payload.getId(), HttpStatus.CREATED);
    }
 }
