package com.mdx.achievos.service;

import com.mdx.achievos.dto.BadgeRequest;
import com.mdx.achievos.entity.Badge;

import java.util.List;

public interface BadgeService {

    List<Badge> getAllBadges();

    String addBadge(BadgeRequest request);

    String updateBadge(Long badgeId, BadgeRequest request);

    String deleteBadge(Long badgeId);

}
