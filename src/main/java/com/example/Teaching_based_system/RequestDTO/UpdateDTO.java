package com.example.Teaching_based_system.RequestDTO;

public class UpdateDTO {
    public String name;
    public String newRole;

    public UpdateDTO() {
    }

    public UpdateDTO(String name, String newRole) {
        this.name = name;
        this.newRole = newRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNewRole() {
        return newRole;
    }

    public void setNewRole(String newRole) {
        this.newRole = newRole;
    }
}
