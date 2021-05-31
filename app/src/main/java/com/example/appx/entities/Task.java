package com.example.appx.entities;

public class Task {
    private String id;
    private String project_id;
    private String content;
    private String priority;
    private String order;
    private boolean completed;
    private String created;

    public Task(String id, String project_id, String content, String priority, String order, boolean completed, String created) {
        this.id = id;
        this.project_id = project_id;
        this.content = content;
        this.priority = priority;
        this.order = order;
        this.completed = completed;
        this.created = created;
    }

    public Task(String id, String project_id, String content) {
        this.id = id;
        this.project_id = project_id;
        this.content = content;
    }

    public Task(String project_id, String content) {
        this.project_id = project_id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getProject_id() {
        return project_id;
    }

    public String getContent() {
        return content;
    }

    public String getPriority() {
        return priority;
    }

    public String getOrder() {
        return order;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getCreated() {
        return created;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
