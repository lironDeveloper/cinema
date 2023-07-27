package com.cinema.galaxy.DTOs;

import com.cinema.galaxy.models.User;

public class UserCreationDTO {
    private String username;
    private String password;
    private String role;

    public UserCreationDTO() {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
