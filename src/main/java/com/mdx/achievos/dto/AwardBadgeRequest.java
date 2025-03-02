package com.mdx.achievos.dto;

import com.mdx.achievos.entity.enums.BadgeStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

public class AwardBadgeRequest {

    private final LocalDateTime earnedAt = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private BadgeStatus status = BadgeStatus.valueOf("ACTIVE");
    private String comments;
    private Boolean isFavorite;

    private Long badgeId;
    private Long userId;
    private Long grantedBy;

    public AwardBadgeRequest() {
    }

    public LocalDateTime getEarnedAt() {
        return earnedAt;
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

    public Long getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(Long badgeId) {
        this.badgeId = badgeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGrantedBy() {
        return grantedBy;
    }

    public void setGrantedBy(Long grantedBy) {
        this.grantedBy = grantedBy;
    }
}
