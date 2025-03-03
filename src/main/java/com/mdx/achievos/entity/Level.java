package com.mdx.achievos.entity;

import com.mdx.achievos.entity.enums.Levels;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "levels")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long levelId;

    @Enumerated(EnumType.STRING)
    private Levels levelName;

    private Integer minXp;
    private Integer maxXp;

    @Setter(AccessLevel.NONE)
    private final LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    @PreUpdate
    private void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
