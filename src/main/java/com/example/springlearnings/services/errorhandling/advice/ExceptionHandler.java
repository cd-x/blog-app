package com.example.springlearnings.services.errorhandling.advice;

import com.example.springlearnings.services.errorhandling.exceptions.UserDoesNotExistException;
import com.example.springlearnings.services.errorhandling.model.ErrorResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<ErrorResponseBody> handleUserDoesNotExistException(UserDoesNotExistException ex) {
        ErrorResponseBody responseBody = new ErrorResponseBody(HttpStatus.BAD_REQUEST.toString(), ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(responseBody);
    }
}
