package com.mdx.achievos.entity.enums;

import lombok.Getter;

@Getter
public enum BadgeTier {
    IRON(0),
    BRONZE(1),
    SILVER(2),
    GOLD(3),
    PLATINUM(4),
    DIAMOND(5);

    private final int rarity;

    BadgeTier(int rarity) {
        this.rarity = rarity;
    }

}
