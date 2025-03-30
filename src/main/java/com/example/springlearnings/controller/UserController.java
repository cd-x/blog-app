package com.example.springlearnings.controller;


import com.example.springlearnings.entity.Journal;
import com.example.springlearnings.entity.User;
import com.example.springlearnings.services.errorhandling.exceptions.UserAlreadyExistException;
import com.example.springlearnings.services.interfaces.IUserManagementService;
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
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public List<User> getAllUsers(){
        List<User> users = userManagementService.getAllUsers();
        logger.debug("getAllUsers has users :{}", users.size());
        return users;
    }

    @GetMapping(path = "/{username}")
    public User getUserById(@PathVariable String username){
        logger.debug("retrieving user with username: {}", username);
        return userManagementService.getUserByUserName(username);
    }
    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody User user){
        try {
            userManagementService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (UserAlreadyExistException userAlreadyExistException){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userAlreadyExistException.getMessage());
        }
    }
    @DeleteMapping(path = "/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username){
        logger.debug("deleting user with id: {}", username);
        userManagementService.deleteUser(username);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping(path = "/{username}/journal")
    public ResponseEntity<List<Journal>> getJournalsByUsername(@PathVariable String username){
        User user = userManagementService.getUserByUserName(username);
        return new ResponseEntity<>(user.getJournalList(), HttpStatus.OK);
    }
}
