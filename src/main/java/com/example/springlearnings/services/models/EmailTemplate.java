package com.example.springlearnings.services.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailTemplate {
    private String email;
    private String subject;
    private String body;
}
