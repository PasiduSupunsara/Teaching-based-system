package com.example.Teaching_based_system.Auth;

public class AuthRequest {
    private String name;
    private String password;

    public AuthRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public AuthRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
