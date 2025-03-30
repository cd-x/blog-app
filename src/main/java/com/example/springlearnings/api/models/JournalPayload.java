package com.example.springlearnings.api.models;


public class JournalPayload {
    String title;
    String content;
    String authorUserName;

    public String getAuthorUserName() {
        return authorUserName;
    }

    public void setAuthorUserName(String authorUserName) {
        this.authorUserName = authorUserName;
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
                ", authorUserName='" + authorUserName + '\'' +
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
