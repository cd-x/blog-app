package com.example.springlearnings.entity;

import lombok.Data;

@Data
public class JournalSearchDTO {
    private String id;
    private String title;
    private String content;
    private String username;
}
