package com.mdx.achievos.api;

import com.mdx.achievos.dto.AwardBadgeRequest;
import com.mdx.achievos.entity.AwardedBadge;
import com.mdx.achievos.service.interfaces.AwardedBadgeService;
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
    public ResponseEntity<List<AwardedBadge>> getUserBadges(@PathVariable("id") Long userid) {
        return ResponseEntity.ok(awardedBadgeService.getAllUserBadges(userid));
    }

    @GetMapping("/by/{id}")
    public ResponseEntity<List<AwardedBadge>> getUserBadgesByGrantedBy(@PathVariable("id") Long grantedBy) {
        return ResponseEntity.ok(awardedBadgeService.getAllUserBadgesByGrantedBy(grantedBy));
    }

    @PostMapping
    public String awardBadge(@RequestBody AwardBadgeRequest request) {
        System.out.println(request);
        System.out.println(request.getUserId());
        System.out.println(request.getGrantedBy());
        System.out.println(request.getBadgeId());
        return awardedBadgeService.awardBadge(request);
    }

}
