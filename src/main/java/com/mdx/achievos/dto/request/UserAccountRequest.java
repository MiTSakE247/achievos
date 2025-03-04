package com.mdx.achievos.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountRequest {
    private String name;
    private String username;
    private String userEmail;
    private String passwordHash;
    private String newPasswordHash;
    private String profilePic;
    private String bio;
    // add userStatus (Enum type and test later)
}
