package com.mdx.achievos.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaderboardResponse {
    private Long userId;
    private String username;
    private Integer totalXp;
}
