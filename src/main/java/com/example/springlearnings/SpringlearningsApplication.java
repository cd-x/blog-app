package com.example.springlearnings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties
public class SpringlearningsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringlearningsApplication.class, args);
    }

}
