package com.example.springlearnings.services.interfaces;

public interface IEmailService {
    void sendEmail(String to, String subject, String body);
}
