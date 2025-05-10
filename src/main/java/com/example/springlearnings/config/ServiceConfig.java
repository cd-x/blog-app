package com.example.springlearnings.config;

import com.example.springlearnings.services.impl.EmailService;
import com.example.springlearnings.services.interfaces.IJournalSearchService;
import com.example.springlearnings.services.pricing.models.Pricing;
import com.example.springlearnings.services.search.JournalSearchService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public EmailService emailService() {
        return new EmailService();
    }

    @Bean
    public Pricing pricing() {
        return new Pricing();
    }

    @Bean
    public IJournalSearchService journalSearchService() {
        return new JournalSearchService();
    }
}
