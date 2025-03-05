package com.mdx.achievos.service.impl;

import com.mdx.achievos.dto.request.BadgeRequest;
import com.mdx.achievos.dto.response.BadgeResponse;
import com.mdx.achievos.entity.Badge;
import com.mdx.achievos.exception.BadRequestException;
import com.mdx.achievos.exception.DuplicateResourceException;
import com.mdx.achievos.exception.EntityNotFoundException;
import com.mdx.achievos.repo.BadgeRepo;
import com.mdx.achievos.service.interfaces.BadgeService;
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
    public BadgeResponse addBadge(BadgeRequest request) {
        if (Objects.isNull(request)) {
            throw new BadRequestException("Request body is empty!");
        }

        if (isEmpty(request.getBadgeName())) {
            throw new BadRequestException("Required fields are missing or empty.");
        }

        if (badgeRepo.existsByBadgeName(request.getBadgeName())) {
            throw new DuplicateResourceException("Badge with this name already exists.");
        }

        Badge badge = createBadge(request);
        badgeRepo.save(badge);

        return convertToResponseDTO(badge);
    }

    @Override
    public BadgeResponse updateBadge(Long badgeId, BadgeRequest request) {
        if (Objects.isNull(request)) {
            throw new BadRequestException("Request body is empty!");
        }

        if (isEmpty(request.getBadgeName())) {
            throw new BadRequestException("Required fields are missing or empty.");
        }

        Badge badge = badgeRepo.findById(badgeId)
                .orElseThrow(() -> new EntityNotFoundException("Badge not found with ID: " + badgeId));

        if (!request.getBadgeName().equals(badge.getBadgeName()) && badgeRepo.existsByBadgeName(request.getBadgeName())) {
            throw new DuplicateResourceException("Badge with this name already exists.");
        }

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

        return convertToResponseDTO(badge);
    }

    @Override
    public void deleteBadge(Long badgeId) {
        Badge badge = badgeRepo.findById(badgeId)
                .orElseThrow(() -> new EntityNotFoundException("Badge not found with ID: " + badgeId));

        badgeRepo.delete(badge);
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

    private BadgeResponse convertToResponseDTO(Badge badge) {
        return new BadgeResponse(
                badge.getBadgeId(),
                badge.getBadgeName(),
                badge.getBadgeDescription(),
                badge.getBadgeImg(),
                badge.getBadgeXp(),
                badge.getBadgeTier(),
                badge.getBadgeCategory(),
                badge.getCreatedAt(),
                badge.getUpdatedAt()
        );
    }

    public Badge getBadgeById(Long badgeId) {
        return badgeRepo.findById(badgeId).orElseThrow(() -> new EntityNotFoundException("Badge not found with ID: " + badgeId));
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
