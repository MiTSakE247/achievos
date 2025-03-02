package com.mdx.achievos.dto;

import com.mdx.achievos.entity.enums.BadgeCategory;
import com.mdx.achievos.entity.enums.BadgeTier;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BadgeRequest {
    private String badgeName;
    private String badgeDescription;
    private String badgeImg;
    private Integer badgeXp;
    @Enumerated(EnumType.STRING)
    private BadgeTier badgeTier;
    @Enumerated(EnumType.STRING)
    private BadgeCategory badgeCategory;
    private final LocalDateTime updatedAt = LocalDateTime.now();

    public BadgeRequest() {
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    public String getBadgeDescription() {
        return badgeDescription;
    }

    public void setBadgeDescription(String badgeDescription) {
        this.badgeDescription = badgeDescription;
    }

    public String getBadgeImg() {
        return badgeImg;
    }

    public void setBadgeImg(String badgeImg) {
        this.badgeImg = badgeImg;
    }

    public Integer getBadgeXp() {
        return badgeXp;
    }

    public void setBadgeXp(Integer badgeXp) {
        this.badgeXp = badgeXp;
    }

    public BadgeTier getBadgeTier() {
        return badgeTier;
    }

    public void setBadgeTier(BadgeTier badgeTier) {
        this.badgeTier = badgeTier;
    }

    public BadgeCategory getBadgeCategory() {
        return badgeCategory;
    }

    public void setBadgeCategory(BadgeCategory badgeCategory) {
        this.badgeCategory = badgeCategory;
    }

    @Override
    public String toString() {
        return "BadgeRequest{" +
                "badgeName='" + badgeName + '\'' +
                ", badgeDescription='" + badgeDescription + '\'' +
                ", badgeImg='" + badgeImg + '\'' +
                ", badgeXp=" + badgeXp +
                ", badgeTier=" + badgeTier +
                ", badgeCategory=" + badgeCategory +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
