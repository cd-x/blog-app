package com.example.springlearnings.services.impl;

import com.example.springlearnings.services.interfaces.IEmailService;
import com.example.springlearnings.services.models.EmailTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EmailService implements IEmailService {

    @Override
    public void sendEmails(List<EmailTemplate> emailTemplateList) {
        emailTemplateList.forEach(emailTemplate -> {
            log.info("Sending email...!");
            log.info("Sent email to {}", emailTemplate.getEmail());
        });
    }
}
