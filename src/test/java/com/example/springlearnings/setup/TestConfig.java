package com.example.springlearnings.setup;

import com.example.springlearnings.config.JournalRepositoryMock;
import com.example.springlearnings.config.UserRepositoryMock;
import com.example.springlearnings.persistence.IJournalRepository;
import com.example.springlearnings.persistence.IUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
    @Bean
    IUserRepository userRepository() {
        return new UserRepositoryMock();
    }

    @Bean
    IJournalRepository journalRepository() {
        return new JournalRepositoryMock();
    }
}
