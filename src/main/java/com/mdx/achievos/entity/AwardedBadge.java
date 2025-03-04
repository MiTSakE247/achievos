package com.mdx.achievos.entity;

import com.mdx.achievos.entity.enums.BadgeStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "awarded_badges")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AwardedBadge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long awardedBadgeId;

    private LocalDateTime earnedAt;

    @Enumerated(EnumType.STRING)
    private BadgeStatus status;

    private String comments;
    private Boolean isFavorite;

    private Long badgeId;
    private Long userId;
    private Long grantedBy;
}
