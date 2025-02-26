package com.mdx.achievos.achievos.dto;

public class UserAccountRequest {
    private String username;
    private String userEmail;
    private String passwordHash;

    public UserAccountRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public String toString() {
        return "UserRequestAccount{" +
                "username='" + username + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }
}
