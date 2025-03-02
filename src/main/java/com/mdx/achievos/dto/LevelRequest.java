package com.mdx.achievos.dto;

import com.mdx.achievos.entity.enums.Levels;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

public class LevelRequest {
    private Long userId;
    @Enumerated(EnumType.STRING)
    private Levels levelName;
    private Integer minXp;
    private Integer maxXp;
    private LocalDateTime updatedAt = LocalDateTime.now();

    public LevelRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Levels getLevelName() {
        return levelName;
    }

    public void setLevelName(Levels levelName) {
        this.levelName = levelName;
    }

    public Integer getMinXp() {
        return minXp;
    }

    public void setMinXp(Integer minXp) {
        this.minXp = minXp;
    }

    public Integer getMaxXp() {
        return maxXp;
    }

    public void setMaxXp(Integer maxXp) {
        this.maxXp = maxXp;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }


    @Override
    public String toString() {
        return "LevelRequest{" +
                "userId=" + userId +
                ", levelName=" + levelName +
                ", minXp=" + minXp +
                ", maxXp=" + maxXp +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
