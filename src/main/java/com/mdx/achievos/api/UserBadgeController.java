package com.mdx.achievos.api;

import com.mdx.achievos.dto.AwardBadgeRequest;
import com.mdx.achievos.entity.Badge;
import com.mdx.achievos.entity.User;
import com.mdx.achievos.service.UserBadgeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/award-badge")
public class UserBadgeController {

    private final UserBadgeService userBadgeService;

    public UserBadgeController(UserBadgeService userBadgeService) {
        this.userBadgeService = userBadgeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Badge>> getBadges(@PathVariable("id") Long userid) {
        return ResponseEntity.ok(userBadgeService.getAllUserBadges(userid));
    }

    @PostMapping
    public String awardBadge(@RequestBody AwardBadgeRequest request) {
        System.out.println(request);
        System.out.println(request.getUserId());
        System.out.println(request.getGrantedBy());
        System.out.println(request.getBadgeId());
        return userBadgeService.awardBadge(request);
    }

}
