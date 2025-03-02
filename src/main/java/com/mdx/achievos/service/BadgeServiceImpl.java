package com.mdx.achievos.service;

import com.mdx.achievos.dto.BadgeRequest;
import com.mdx.achievos.entity.Badge;
import com.mdx.achievos.repo.BadgeRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BadgeServiceImpl implements BadgeService {

    private final BadgeRepo badgeRepo;

    public BadgeServiceImpl(BadgeRepo badgeRepo) {
        this.badgeRepo = badgeRepo;
    }

    @Override
    public List<Badge> getAllBadges() {
        return badgeRepo.findAll();
    }

    @Override
    public String addBadge(BadgeRequest request) {
        if (Objects.isNull(request)) {
            return "Request body empty!";
        }

        Badge badge = createBadge(request);

        badgeRepo.save(badge);
        return "Badge successfully saved";
    }

    @Override
    public String updateBadge(Long badgeId, BadgeRequest request) {
        Optional<Badge> optionalBadge = badgeRepo.findById(badgeId);

        if (optionalBadge.isEmpty()) {
            return "Badge does not exist";
        }

        Badge badge = optionalBadge.get();
        badge.setUpdatedAt(request.getUpdatedAt());

        if (request.getBadgeName() != null) {
            badge.setBadgeName(request.getBadgeName());
        }

        if (request.getBadgeDescription() != null) {
            badge.setBadgeDescription(request.getBadgeDescription());
        }

        if (request.getBadgeImg() != null) {
            badge.setBadgeImg(request.getBadgeImg());
        }

        if (request.getBadgeXp() != null) {
            badge.setBadgeXp(request.getBadgeXp());
        }

        if (request.getBadgeTier() != null) {
            badge.setBadgeTier(request.getBadgeTier());
        }

        if (request.getBadgeCategory() != null) {
            badge.setBadgeCategory(request.getBadgeCategory());
        }

        badgeRepo.save(badge);
        return "Badge updated successfully";
    }

    @Override
    public String deleteBadge(Long badgeId) {
        Optional<Badge> optionalBadge = badgeRepo.findById(badgeId);

        if (optionalBadge.isEmpty()) {
            return "Badge does not exist";
        }

        Badge badge = optionalBadge.get();
        badgeRepo.delete(badge);
        return "Badge deleted successfully";
    }

    private Badge createBadge(BadgeRequest request) {
        Badge badge = new Badge();

        badge.setBadgeName(request.getBadgeName());
        badge.setBadgeDescription(request.getBadgeDescription());
        badge.setBadgeImg(request.getBadgeImg());
        badge.setBadgeXp(request.getBadgeXp());
        badge.setBadgeTier(request.getBadgeTier());
        badge.setBadgeCategory(request.getBadgeCategory());

        return badge;
    }

}
