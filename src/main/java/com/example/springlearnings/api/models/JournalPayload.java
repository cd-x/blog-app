package com.example.springlearnings.api.models;


public class JournalPayload {
    String title;
    String content;
    String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "JournalPayload{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", authorUserName='" + username + '\'' +
                '}';
    }

    public JournalPayload(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
