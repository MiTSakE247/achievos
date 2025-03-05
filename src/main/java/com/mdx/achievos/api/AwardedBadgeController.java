package com.mdx.achievos.api;

import com.mdx.achievos.dto.ApiResponse;
import com.mdx.achievos.dto.request.AwardedBadgeRequest;
import com.mdx.achievos.dto.response.AwardedBadgeResponse;
import com.mdx.achievos.dto.response.LeaderboardResponse;
import com.mdx.achievos.entity.AwardedBadge;
import com.mdx.achievos.service.interfaces.AwardedBadgeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/award-badge")
public class AwardedBadgeController {

    private final AwardedBadgeService awardedBadgeService;

    public AwardedBadgeController(AwardedBadgeService awardedBadgeService) {
        this.awardedBadgeService = awardedBadgeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<List<AwardedBadge>>> getUserBadges(@PathVariable("id") Long userid) {
        List<AwardedBadge> badges = awardedBadgeService.getAllUserBadges(userid);
        return ResponseEntity.ok(new ApiResponse<>(true, "User badges retrieved successfully", badges));
    }

    @GetMapping("/by/{id}")
    public ResponseEntity<ApiResponse<List<AwardedBadge>>> getUserBadgesByGrantedBy(@PathVariable("id") Long grantedBy) {
        List<AwardedBadge> badges = awardedBadgeService.getAllUserBadgesByGrantedBy(grantedBy);
        return ResponseEntity.ok(new ApiResponse<>(true, "Badges granted by user retrieved successfully", badges));
    }

    @GetMapping("/recent")
    public ResponseEntity<ApiResponse<List<AwardedBadgeResponse>>> getRecentAwardedBadges() {
        List<AwardedBadgeResponse> badges = awardedBadgeService.getRecentAwardedBadges();
        return ResponseEntity.ok(new ApiResponse<>(true, "Recent badges retrieved successfully", badges));
    }

    @GetMapping("/leaderboard/xp")
    public ResponseEntity<ApiResponse<List<LeaderboardResponse>>> getLeaderboardByXp() {
        List<LeaderboardResponse> leaderboard = awardedBadgeService.getLeaderboardByXp();
        return ResponseEntity.ok(new ApiResponse<>(true, "Leaderboard retrieved successfully", leaderboard));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AwardedBadgeResponse>> awardBadge(@RequestBody AwardedBadgeRequest request) {
        AwardedBadgeResponse awardedBadge = awardedBadgeService.awardBadge(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Badge awarded successfully", awardedBadge));
    }

}
