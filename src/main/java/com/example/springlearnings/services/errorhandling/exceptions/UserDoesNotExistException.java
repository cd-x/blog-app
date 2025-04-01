package com.example.springlearnings.services.errorhandling.exceptions;

public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException() {
        super("User does not exist!!");
    }
}
