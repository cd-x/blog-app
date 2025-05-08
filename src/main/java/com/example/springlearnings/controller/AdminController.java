package com.example.springlearnings.controller;

import com.example.springlearnings.entity.Journal;
import com.example.springlearnings.entity.User;
import com.example.springlearnings.services.interfaces.IJournalManagementService;
import com.example.springlearnings.services.interfaces.IUserManagementService;
import com.example.springlearnings.services.pricing.interfaces.IJournalPricingService;
import com.example.springlearnings.services.pricing.models.Pricing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IJournalManagementService journalManagementService;
    @Autowired
    private IUserManagementService userManagementService;
    @Autowired
    private IJournalPricingService journalPricingService;

    @GetMapping("/all-users")
    public List<User> getAllUsers() {
        return userManagementService.getAllUsers();
    }

    @GetMapping("/all-journals")
    public List<Journal> getAllJournals() {
        return journalManagementService.getJournals();
    }

    @PostMapping(path = "/pricing")
    public ResponseEntity<?> updatePricing(@RequestBody List<Pricing> pricingList) {
        journalPricingService.updatePricing(pricingList);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
