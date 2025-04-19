package com.example.springlearnings.services;

import com.example.springlearnings.services.impl.EmailService;
import com.example.springlearnings.setup.TestSetup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailServiceTest extends TestSetup {

    @Autowired
    private EmailService emailService;


    @Test
    public void testEmail() {
    }
}
