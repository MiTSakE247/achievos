package com.mdx.achievos.api;

import com.mdx.achievos.dto.ApiResponse;
import com.mdx.achievos.dto.request.BadgeRequest;
import com.mdx.achievos.dto.response.BadgeResponse;
import com.mdx.achievos.entity.Badge;
import com.mdx.achievos.exception.BadRequestException;
import com.mdx.achievos.service.interfaces.BadgeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/api/badges")
public class BadgeController {

    private final BadgeService badgeService;

    public BadgeController(BadgeService badgeService) {
        this.badgeService = badgeService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Badge>>> getAll() {
        List<Badge> badges = badgeService.getAllBadges();
        return ResponseEntity.ok(new ApiResponse<>(true, "Badges retrieved successfully", badges));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BadgeResponse>> addBadge(@RequestBody BadgeRequest request) {
        if (Objects.isNull(request)) {
            throw new BadRequestException("Request body is empty.");
        }

        if (isEmpty(request.getBadgeName())){
            throw new BadRequestException("Required fields are missing or empty.");
        }

        BadgeResponse badge = badgeService.addBadge(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Badge created successfully", badge));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BadgeResponse>> updateBadge(@PathVariable("id") Long badgeId, @RequestBody BadgeRequest request) {
        BadgeResponse updatedBadge = badgeService.updateBadge(badgeId, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Badge updated successfully", updatedBadge));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteBadge(@PathVariable("id") Long badgeId) {
        badgeService.deleteBadge(badgeId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Badge deleted successfully", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Badge>> getBadgeById(@PathVariable("id") Long badgeId) {
        Badge badge = badgeService.getBadgeById(badgeId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Badge retrieved successfully", badge));
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

}
