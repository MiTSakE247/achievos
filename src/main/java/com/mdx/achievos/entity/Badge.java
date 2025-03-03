package com.mdx.achievos.entity;

import com.mdx.achievos.entity.enums.BadgeCategory;
import com.mdx.achievos.entity.enums.BadgeTier;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "badges")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Setter(AccessLevel.NONE)
    private final LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    @PreUpdate
    private void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
