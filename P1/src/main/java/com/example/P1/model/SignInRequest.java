package com.example.P1.model;

public class SignInRequest {
    // Getters and setters
    private String email;
    private String password;

    public SignInRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public SignInRequest() {
    }

    @Override
    public String toString() {
        return "Request with password: " + this.password + "and email: " + this.email + "\n";
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
