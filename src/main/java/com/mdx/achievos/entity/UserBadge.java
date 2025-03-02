package com.mdx.achievos.entity;

import com.mdx.achievos.entity.enums.BadgeStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="userbadges")
public class UserBadge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userBadgeId;
    private LocalDateTime earnedAt;
    @Enumerated(EnumType.STRING)
    private BadgeStatus status;
    private String comments;
    private Boolean isFavorite;

    private Long badgeId;
    private Long userId;
    private Long grantedBy;

    public UserBadge() {
    }

    public Long getGrantedBy() {
        return grantedBy;
    }

    public void setGrantedBy(Long grantedBy) {
        this.grantedBy = grantedBy;
    }

    public LocalDateTime getEarnedAt() {
        return earnedAt;
    }

    public void setEarnedAt(LocalDateTime earnedAt) {
        this.earnedAt = earnedAt;
    }

    public Long getUserBadgeId() {
        return userBadgeId;
    }

    public void setUserBadgeId(Long userBadgeId) {
        this.userBadgeId = userBadgeId;
    }

    public BadgeStatus getStatus() {
        return status;
    }

    public void setStatus(BadgeStatus status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(Long badgeId) {
        this.badgeId = badgeId;
    }

    @Override
    public String toString() {
        return "UserBadge{" +
                "userBadgeId=" + userBadgeId +
                ", earnedAt=" + earnedAt +
                ", status=" + status +
                ", comments='" + comments + '\'' +
                ", isFavorite=" + isFavorite +
                ", badgeId=" + badgeId +
                ", userId=" + userId +
                ", grantedBy=" + grantedBy +
                '}';
    }
}
