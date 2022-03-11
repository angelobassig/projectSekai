package com.company.discussion.models;

import java.io.Serializable;

public class JwtRequest implements Serializable {

    // Properties
    private static final long serialVersionUID = 5926468583005150707L;

    private String email;
    private String password;

    // Constructors
    public JwtRequest() {
    }

    public JwtRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
