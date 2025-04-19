package com.example.springlearnings.services.models;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "config.email")
public class EmailConfiguration {
    String host;
    Integer port;
    String username;
    String password;
}
