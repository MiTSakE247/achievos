package com.mdx.achievos.dto;

import java.time.LocalDateTime;

public class UserAccountRequest {
    private String name;
    private String username;
    private String userEmail;
    private String passwordHash;
    private String newPasswordHash;
    private final LocalDateTime updatedAt = LocalDateTime.now();
    private String profilePic;
    private String bio;
    // add userStatus (Enum type and test later)

    public UserAccountRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getNewPasswordHash() {
        return newPasswordHash;
    }

    public void setNewPasswordHash(String newPasswordHash) {
        this.newPasswordHash = newPasswordHash;
    }

    @Override
    public String toString() {
        return "UserAccountRequest{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", updatedAt=" + updatedAt +
                ", profilePic='" + profilePic + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}
