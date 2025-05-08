package com.example.springlearnings.services.impl;

import com.example.springlearnings.entity.User;
import com.example.springlearnings.persistence.impl.UserRepositoryImpl;
import com.example.springlearnings.persistence.interfaces.IUserRepository;
import com.example.springlearnings.services.errorhandling.exceptions.UserAlreadyExistException;
import com.example.springlearnings.services.interfaces.IUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserManagementService implements IUserManagementService {
    @Autowired
    private IUserRepository repository;
    @Autowired
    private UserRepositoryImpl userRepository;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User getUserByUserName(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public void createUser(User user) throws UserAlreadyExistException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (CollectionUtils.isEmpty(user.getRoles())) user.setRoles(Collections.singletonList("USER"));
        if (repository.isUserRegistered(user.getUsername()))
            throw new UserAlreadyExistException();
        else
            repository.save(user);
    }

    @Override
    public void deleteUser(String username) {
        repository.deleteByUsername(username);
    }

    @Override
    public Map<String, String> getUsernameEmailMapWithSentimentOn() {
        return userRepository.getUserWithSentimentAnalysis().stream()
                .map(user -> Map.entry(user.getUsername(), user.getEmail()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


}
