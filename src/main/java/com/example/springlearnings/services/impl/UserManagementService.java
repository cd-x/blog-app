package com.example.springlearnings.services.impl;

import com.example.springlearnings.entity.User;
import com.example.springlearnings.persistence.IUserRepository;
import com.example.springlearnings.services.errorhandling.exceptions.UserAlreadyExistException;
import com.example.springlearnings.services.interfaces.IUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagementService implements IUserManagementService {
    @Autowired
    private IUserRepository repository;

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
        if(repository.isUserRegistered(user.getUsername()))
            throw new UserAlreadyExistException();
        else
            repository.save(user);
    }

    @Override
    public void deleteUser(String username) {
        repository.deleteByUsername(username);
    }
}
