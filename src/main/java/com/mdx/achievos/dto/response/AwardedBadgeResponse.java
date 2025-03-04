package com.mdx.achievos.dto.response;

import com.mdx.achievos.entity.enums.BadgeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AwardedBadgeResponse {
    private Long awardedBadgeId;
    private Long userId;
    private Long badgeId;
    private Long grantedBy;
    private BadgeStatus status;
    private String comments;
    private LocalDateTime earnedAt;
}
