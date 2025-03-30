package com.example.springlearnings.services.errorhandling.exceptions;

public class UserAlreadyExistException extends Exception{
    public UserAlreadyExistException(){
        super("Username already exists!");
    }
}