package com.mdx.achievos.dto.request;

import com.mdx.achievos.entity.enums.BadgeStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AwardedBadgeRequest {

    private final LocalDateTime earnedAt = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private final BadgeStatus status = BadgeStatus.valueOf("ACTIVE");
    private String comments;

    private Long badgeId;
    private Long userId;
    private Long grantedBy;
}
