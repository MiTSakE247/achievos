package com.mdx.achievos.service.impl;

import com.mdx.achievos.dto.request.AwardedBadgeRequest;
import com.mdx.achievos.dto.response.AwardedBadgeResponse;
import com.mdx.achievos.entity.AwardedBadge;
import com.mdx.achievos.exception.BadRequestException;
import com.mdx.achievos.exception.EntityNotFoundException;
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
        awardedBadge = userBadgeRepo.save(awardedBadge);

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
