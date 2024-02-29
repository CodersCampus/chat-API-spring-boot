package com.coderscampus.chatapp.dto;

public class UserInformation {
    private String userId;
    private String username;
    // You can add more fields here as per your JWT token structure

    public UserInformation() {
        // Default constructor
    }

    // Constructor using fields
    public UserInformation(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // You might want to override toString(), equals(), and hashCode() methods as well.

    @Override
    public String toString() {
        return "UserInformation{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}

