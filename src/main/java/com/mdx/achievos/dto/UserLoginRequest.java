package com.mdx.achievos.dto;

public class UserLoginRequest {
    private String userEmail;
    private String passwordHash;

    public UserLoginRequest() {
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
        return "UserLoginRequest{" +
                "userEmail='" + userEmail + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }
}
