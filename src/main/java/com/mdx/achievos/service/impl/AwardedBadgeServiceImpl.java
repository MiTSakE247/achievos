package com.mdx.achievos.service.impl;

import com.mdx.achievos.dto.AwardBadgeRequest;
import com.mdx.achievos.entity.AwardedBadge;
import com.mdx.achievos.repo.BadgeRepo;
import com.mdx.achievos.repo.UserBadgeRepo;
import com.mdx.achievos.repo.UserRepo;
import com.mdx.achievos.service.interfaces.AwardedBadgeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AwardedBadgeServiceImpl implements AwardedBadgeService {

    private final UserBadgeRepo userBadgeRepo;
    private final UserRepo userRepo;
    private final BadgeRepo badgeRepo;

    public AwardedBadgeServiceImpl(UserBadgeRepo userBadgeRepo, UserRepo userRepo, BadgeRepo badgeRepo) {
        this.userBadgeRepo = userBadgeRepo;
        this.userRepo = userRepo;
        this.badgeRepo = badgeRepo;
    }

    @Override
    public List<AwardedBadge> getAllUserBadges(Long userId) {
//        List<UserBadge> userBadges = userBadgeRepo.findAllUserBadgeByUserId(userId);
//        List<Badge> badges = new ArrayList<>();
//        for (UserBadge ub : userBadges) {
//            // hard coded for now
//            badges.add(badgeRepo.findById(ub.getBadgeId()).orElse(null));
//        }
//        return badges;
        return userBadgeRepo.findAllUserBadgeByUserId(userId);
    }

    @Override
    public List<AwardedBadge> getAllUserBadgesByGrantedBy(Long grantedBy) {
        return userBadgeRepo.findAllUserBadgeByGrantedBy(grantedBy);
    }

    @Override
    public String awardBadge(AwardBadgeRequest request) {
        if (Objects.isNull(request)) {
            return "Request is empty!";
        }

        Long userId = request.getUserId();
        Long grantedBy = request.getGrantedBy();
        Long badgeId = request.getBadgeId();

        System.out.println(userId + " " + grantedBy + " " + badgeId);
        // compare grantedBy User should have higher role level to give badge
        userBadgeRepo.save(createUserBadge(request));
        return "Badge awarded successfully!";
    }

    public AwardedBadge createUserBadge(AwardBadgeRequest request) {
        AwardedBadge awardedBadge = new AwardedBadge();

        awardedBadge.setUserId(request.getUserId());
        awardedBadge.setBadgeId(request.getBadgeId());
        awardedBadge.setComments(request.getComments());
        awardedBadge.setEarnedAt(request.getEarnedAt());
        awardedBadge.setGrantedBy(request.getGrantedBy());

        return awardedBadge;
    }
}
