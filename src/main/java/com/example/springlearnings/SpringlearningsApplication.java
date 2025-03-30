package com.example.springlearnings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringlearningsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringlearningsApplication.class, args);
	}

}
