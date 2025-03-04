package com.mdx.achievos.service.interfaces;

import com.mdx.achievos.dto.request.BadgeRequest;
import com.mdx.achievos.dto.response.BadgeResponse;
import com.mdx.achievos.entity.Badge;

import java.util.List;

public interface BadgeService {

    List<Badge> getAllBadges();

    BadgeResponse addBadge(BadgeRequest request);

    BadgeResponse updateBadge(Long badgeId, BadgeRequest request);

    void deleteBadge(Long badgeId);

    Badge getBadgeById(Long badgeId);
}
