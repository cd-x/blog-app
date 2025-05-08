package com.example.springlearnings.controller;


import com.example.springlearnings.entity.Journal;
import com.example.springlearnings.entity.User;
import com.example.springlearnings.services.errorhandling.exceptions.UserAlreadyExistException;
import com.example.springlearnings.services.interfaces.IUserManagementService;
import com.example.springlearnings.services.pricing.interfaces.IJournalPricingService;
import com.example.springlearnings.services.pricing.models.Pricing;
import com.example.springlearnings.utils.ControllerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private IUserManagementService userManagementService;
    @Autowired
    private IJournalPricingService journalPricingService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public User getUserById() {
        String username = ControllerUtils.getUsernameFromSecurityContext();
        logger.debug("retrieving user with username: {}", username);
        return userManagementService.getUserByUserName(username);
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            userManagementService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (UserAlreadyExistException userAlreadyExistException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userAlreadyExistException.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser() {
        String username = ControllerUtils.getUsernameFromSecurityContext();
        logger.debug("deleting user with id: {}", username);
        userManagementService.deleteUser(username);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(path = "/journal")
    public ResponseEntity<List<Journal>> getJournalsByUsername() {
        User user = userManagementService.getUserByUserName(ControllerUtils.getUsernameFromSecurityContext());
        return new ResponseEntity<>(user.getJournalList(), HttpStatus.OK);
    }

    @GetMapping(path = "/pricing")
    public ResponseEntity<List<Pricing>> getPricingList() {
        return ResponseEntity.ok(journalPricingService.getPricing());
    }
}
