package com.mdx.achievos.entity.enums;

import lombok.Getter;

@Getter
public enum BadgeStatus {
    ACTIVE(1),
    REVOKE(0);

    private final int status;

    BadgeStatus(int status) {
        this.status = status;
    }

}
