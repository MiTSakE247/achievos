package com.mdx.achievos.dto.request;

import com.mdx.achievos.entity.enums.BadgeCategory;
import com.mdx.achievos.entity.enums.BadgeTier;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BadgeRequest {
    private String badgeName;
    private String badgeDescription;
    private String badgeImg;
    private Integer badgeXp;
    @Enumerated(EnumType.STRING)
    private BadgeTier badgeTier;
    @Enumerated(EnumType.STRING)
    private BadgeCategory badgeCategory;
}
