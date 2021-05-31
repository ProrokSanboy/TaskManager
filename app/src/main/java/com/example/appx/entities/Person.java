package com.example.appx.entities;

import java.util.ArrayList;

public class Person {
    private String name;
    private String surname;
    private String post;
    private String login;
    private String password;
    private ArrayList<Task> personalTasks;

    public Person(String name, String surname, String post, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.post = post;
        this.login = login;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPost() {
        return post;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Task> getPersonalTasks() {
        return personalTasks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPersonalTasks(ArrayList<Task> personalTasks) {
        this.personalTasks = personalTasks;
    }
}
