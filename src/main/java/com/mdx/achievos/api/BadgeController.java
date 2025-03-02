package com.mdx.achievos.api;

import com.mdx.achievos.dto.BadgeRequest;
import com.mdx.achievos.entity.Badge;
import com.mdx.achievos.service.BadgeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/badges")
public class BadgeController {

    private final BadgeService badgeService;

    public BadgeController(BadgeService badgeService) {
        this.badgeService = badgeService;
    }

    @GetMapping
    public ResponseEntity<List<Badge>> getAll() {
        return ResponseEntity.ok(badgeService.getAllBadges());
    }

    @PostMapping
    public String addBagde(@RequestBody BadgeRequest request) {
        return badgeService.addBadge(request);
    }

    @PutMapping("/{id}")
    public String updateBadge(@PathVariable("id") Long badgeId, @RequestBody BadgeRequest request) {
        return badgeService.updateBadge(badgeId, request);
    }

    @DeleteMapping("/{id}")
    public String deleteBadge(@PathVariable("id") Long badgeId) {
        return badgeService.deleteBadge(badgeId);
    }
}
