package com.mdx.achievos.dto.request;

import com.mdx.achievos.entity.enums.Levels;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LevelRequest {
    private Long userId;
    @Enumerated(EnumType.STRING)
    private Levels levelName;
    private Integer minXp;
    private Integer maxXp;
    private LocalDateTime updatedAt = LocalDateTime.now();

}
