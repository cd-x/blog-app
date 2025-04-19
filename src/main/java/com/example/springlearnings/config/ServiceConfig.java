package com.example.springlearnings.config;

import com.example.springlearnings.services.impl.EmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public EmailService emailService() {
        return new EmailService();
    }
}
