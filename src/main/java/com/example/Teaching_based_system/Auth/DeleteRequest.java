package com.example.Teaching_based_system.Auth;

public class DeleteRequest {
    private String name;

    public DeleteRequest(String name) {
        this.name = name;
    }

    public DeleteRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
