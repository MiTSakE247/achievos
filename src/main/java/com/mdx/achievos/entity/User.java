package com.mdx.achievos.entity;

import com.mdx.achievos.entity.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    private String username;
    private String userEmail;
    private String passwordHash;
    private String profilePic;
    private String bio;

    @Setter(AccessLevel.NONE)
    private final LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;

    @Enumerated(EnumType.STRING)
    private UserStatus activityStatus;

    private Long roleId; // Foreign Key for Roles Table
    private Long levelId; // Foreign Key for Levels Table

    @PreUpdate
    private void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
