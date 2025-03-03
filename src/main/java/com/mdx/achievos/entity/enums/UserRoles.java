package com.mdx.achievos.entity.enums;

import lombok.Getter;

@Getter
public enum UserRoles {
    INTERN(0),
    EMPLOYEE(1),
    TEAM_LEAD(2),
    SUPERVISOR(3),
    MANAGER(4),
    DIRECTOR(5),
    VP(6),
    C_LEVEL(7);

    private final int level;

    UserRoles(int level) {
        this.level = level;
    }

}
