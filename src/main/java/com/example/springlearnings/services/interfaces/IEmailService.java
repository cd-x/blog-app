package com.example.springlearnings.services.interfaces;

import com.example.springlearnings.services.models.EmailTemplate;

import java.util.List;

public interface IEmailService {
    void sendEmails(List<EmailTemplate> emailTemplateList);
}
