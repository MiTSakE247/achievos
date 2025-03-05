package com.mdx.achievos.service.impl;

import com.mdx.achievos.dto.request.AwardedBadgeRequest;
import com.mdx.achievos.dto.response.AwardedBadgeResponse;
import com.mdx.achievos.dto.response.LeaderboardResponse;
import com.mdx.achievos.entity.AwardedBadge;
import com.mdx.achievos.exception.BadRequestException;
import com.mdx.achievos.exception.EntityNotFoundException;
import com.mdx.achievos.repo.BadgeRepo;
import com.mdx.achievos.repo.AwardedBadgeRepo;
import com.mdx.achievos.repo.UserRepo;
import com.mdx.achievos.service.interfaces.AwardedBadgeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AwardedBadgeServiceImpl implements AwardedBadgeService {

    private final AwardedBadgeRepo awardedBadgeRepo;
    private final UserRepo userRepo;
    private final BadgeRepo badgeRepo;

    public AwardedBadgeServiceImpl(AwardedBadgeRepo awardedBadgeRepo, UserRepo userRepo, BadgeRepo badgeRepo) {
        this.awardedBadgeRepo = awardedBadgeRepo;
        this.userRepo = userRepo;
        this.badgeRepo = badgeRepo;
    }

    @Override
    public List<AwardedBadge> getAllUserBadges(Long userId) {
        return awardedBadgeRepo.findAllUserBadgeByUserId(userId);
    }

    @Override
    public List<AwardedBadge> getAllUserBadgesByGrantedBy(Long grantedBy) {
        return awardedBadgeRepo.findAllUserBadgeByGrantedBy(grantedBy);
    }

    @Override
    public AwardedBadgeResponse awardBadge(AwardedBadgeRequest request) {
        if (Objects.isNull(request)) {
            throw new BadRequestException("Request body is empty!");
        }

        Long userId = request.getUserId();
        Long grantedBy = request.getGrantedBy();
        Long badgeId = request.getBadgeId();

        if (!userRepo.existsById(userId)) {
            throw new EntityNotFoundException("User not found with ID: " + userId);
        }

        if (!badgeRepo.existsById(badgeId)) {
            throw new EntityNotFoundException("Badge not found with ID: " + badgeId);
        }

        // Validate if grantedBy has higher authority (TODO: Implement role level validation)
        if (!userRepo.existsById(grantedBy)) {
            throw new EntityNotFoundException("Granting user not found with ID: " + grantedBy);
        }

        AwardedBadge awardedBadge = createUserBadge(request);
        awardedBadge = awardedBadgeRepo.save(awardedBadge);

        return new AwardedBadgeResponse(
                awardedBadge.getAwardedBadgeId(),
                awardedBadge.getUserId(),
                awardedBadge.getBadgeId(),
                awardedBadge.getGrantedBy(),
                awardedBadge.getStatus(),
                awardedBadge.getComments(),
                awardedBadge.getEarnedAt()
        );
    }

    @Override
    public List<AwardedBadgeResponse> getRecentAwardedBadges() {
        List<AwardedBadge> badges = awardedBadgeRepo.findTop50ByOrderByEarnedAtDesc();

        if (badges == null || badges.isEmpty()) {
            throw new EntityNotFoundException("No recent badges found.");
        }

        return badges.stream().map(badge -> new AwardedBadgeResponse(
                badge.getAwardedBadgeId(),
                badge.getUserId(),
                badge.getBadgeId(),
                badge.getGrantedBy(),
                badge.getStatus(),
                badge.getComments(),
                badge.getEarnedAt()
        )).collect(Collectors.toList());
    }

    @Override
    public List<LeaderboardResponse> getLeaderboardByXp() {
        List<Object[]> results = awardedBadgeRepo.getLeaderboardByXpNative();

        if (results == null || results.isEmpty()) {
            throw new EntityNotFoundException("Leaderboard data not found.");
        }

        return results.stream().map(obj -> new LeaderboardResponse(
                ((Number) obj[0]).longValue(),  // userId
                (String) obj[1],               // username
                ((Number) obj[2]).intValue()   // totalXp
        )).toList();
    }


    public AwardedBadge createUserBadge(AwardedBadgeRequest request) {
        AwardedBadge awardedBadge = new AwardedBadge();

        awardedBadge.setUserId(request.getUserId());
        awardedBadge.setBadgeId(request.getBadgeId());
        awardedBadge.setComments(request.getComments());
        awardedBadge.setEarnedAt(request.getEarnedAt());
        awardedBadge.setGrantedBy(request.getGrantedBy());

        return awardedBadge;
    }

}
