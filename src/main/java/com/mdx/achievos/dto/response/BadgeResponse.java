package com.mdx.achievos.dto.response;

import com.mdx.achievos.entity.enums.BadgeCategory;
import com.mdx.achievos.entity.enums.BadgeTier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BadgeResponse {
    private Long badgeId;
    private String badgeName;
    private String badgeDescription;
    private String badgeImg;
    private Integer badgeXp;
    private BadgeTier badgeTier;
    private BadgeCategory badgeCategory;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
