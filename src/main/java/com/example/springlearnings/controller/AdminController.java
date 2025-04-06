package com.example.springlearnings.controller;

import com.example.springlearnings.entity.Journal;
import com.example.springlearnings.entity.User;
import com.example.springlearnings.services.interfaces.IJournalManagementService;
import com.example.springlearnings.services.interfaces.IUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IJournalManagementService journalManagementService;
    @Autowired
    private IUserManagementService userManagementService;

    @GetMapping("/all-users")
    public List<User> getAllUsers() {
        return userManagementService.getAllUsers();
    }

    @GetMapping("/all-journals")
    public List<Journal> getAllJournals() {
        return journalManagementService.getJournals();
    }
}
