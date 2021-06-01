package com.example.appx.entities;

public class Task {
    private int id;
    private String content;
    private String due;
    private String status;
    private String person;

    public Task(String content, String status, String person) {
        this.content = content;
        this.status = status;
        this.person = person;
    }

    public Task(int id, String content, String status, String person) {
        this.id = id;
        this.content = content;
        this.status = status;
        this.person = person;
    }

    public Task(String content, String due, String status, String person) {
        this.content = content;
        this.due = due;
        this.status = status;
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getDue() {
        return due;
    }

    public String getStatus() {
        return status;
    }

    public String getPerson() {
        return person;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
