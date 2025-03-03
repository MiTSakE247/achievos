package com.mdx.achievos.service;

import com.mdx.achievos.dto.AwardBadgeRequest;
import com.mdx.achievos.entity.Badge;
import com.mdx.achievos.entity.UserBadge;
import com.mdx.achievos.repo.BadgeRepo;
import com.mdx.achievos.repo.UserBadgeRepo;
import com.mdx.achievos.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserBadgeServiceImpl implements UserBadgeService {

    private final UserBadgeRepo userBadgeRepo;
    private final UserRepo userRepo;
    private final BadgeRepo badgeRepo;

    public UserBadgeServiceImpl(UserBadgeRepo userBadgeRepo, UserRepo userRepo, BadgeRepo badgeRepo) {
        this.userBadgeRepo = userBadgeRepo;
        this.userRepo = userRepo;
        this.badgeRepo = badgeRepo;
    }

    @Override
    public List<UserBadge> getAllUserBadges(Long userId) {
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
    public List<UserBadge> getAllUserBadgesByGrantedBy(Long grantedBy) {
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

    public UserBadge createUserBadge(AwardBadgeRequest request) {
        UserBadge userBadge = new UserBadge();

        userBadge.setUserId(request.getUserId());
        userBadge.setBadgeId(request.getBadgeId());
        userBadge.setComments(request.getComments());
        userBadge.setEarnedAt(request.getEarnedAt());
        userBadge.setGrantedBy(request.getGrantedBy());

        return userBadge;
    }
}
