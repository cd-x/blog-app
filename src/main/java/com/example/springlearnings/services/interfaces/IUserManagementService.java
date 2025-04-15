package com.example.springlearnings.services.interfaces;

import com.example.springlearnings.entity.User;
import com.example.springlearnings.services.errorhandling.exceptions.UserAlreadyExistException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IUserManagementService {
    List<User> getAllUsers();

    User getUserByUserName(String username);

    void createUser(User user) throws UserAlreadyExistException;

    void deleteUser(String username);

    Map<String, String> getUsernameEmailMapWithSentimentOn();
}
