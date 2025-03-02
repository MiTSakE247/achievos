package com.mdx.achievos.entity;

import com.mdx.achievos.entity.enums.BadgeCategory;
import com.mdx.achievos.entity.enums.BadgeTier;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "badges")
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long badgeId;
    private String badgeName;
    private String badgeDescription;
    private String badgeImg;
    private Integer badgeXp;
    @Enumerated(EnumType.STRING)
    private BadgeTier badgeTier;
    @Enumerated(EnumType.STRING)
    private BadgeCategory badgeCategory;
    private final LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    public Badge() {
    }

    public Long getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(Long badgeId) {
        this.badgeId = badgeId;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Badge{" +
                "badgeId=" + badgeId +
                ", badgeName='" + badgeName + '\'' +
                ", badgeDescription='" + badgeDescription + '\'' +
                ", badgeImg='" + badgeImg + '\'' +
                ", badgeXp=" + badgeXp +
                ", badgeTier=" + badgeTier +
                ", badgeCategory=" + badgeCategory +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
