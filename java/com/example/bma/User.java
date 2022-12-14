package com.example.bma;

import java.util.UUID;

public class User {
    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String fullName;
    private String username;
    private String password;

    public User() {
    }

    public String getFullName() {

        return fullName;
    }

    public void setFullName(String fullName) {

        this.fullName = fullName;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }
}
