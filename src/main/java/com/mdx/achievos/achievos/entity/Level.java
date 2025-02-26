package com.mdx.achievos.achievos.entity;

import com.mdx.achievos.achievos.entity.enums.Levels;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="levels")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long levelId;
    private Enum<Levels> levelName;
    private Integer minXp;
    private Integer maxXp;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    public Level() {

    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public Enum<Levels> getLevelName() {
        return levelName;
    }

    public void setLevelName(Enum<Levels> levelName) {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Level{" +
                "levelId=" + levelId +
                ", levelName=" + levelName +
                ", minXp=" + minXp +
                ", maxXp=" + maxXp +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
