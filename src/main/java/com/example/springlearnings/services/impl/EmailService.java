package com.example.springlearnings.services.impl;

import com.example.springlearnings.services.interfaces.IEmailService;
import com.example.springlearnings.services.models.EmailConfiguration;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Slf4j
@Service
public class EmailService implements IEmailService {

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    private EmailConfiguration emailConfigurations;

    @PostConstruct
    private void init() {
        javaMailSender.setHost(emailConfigurations.getHost());
        javaMailSender.setPort(emailConfigurations.getPort());
        javaMailSender.setUsername(emailConfigurations.getUsername());
        javaMailSender.setPassword(emailConfigurations.getPassword());

        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(body);
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            log.error("Couldn't send email to: {}", to);
        }
    }
}
