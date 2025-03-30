package com.example.springlearnings.services.errorhandling.exceptions;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException(){
        super("Username already exists!");
    }
}