package com.mdx.achievos.service;

import com.mdx.achievos.dto.AwardBadgeRequest;
import com.mdx.achievos.entity.Badge;

import java.util.List;

public interface UserBadgeService {

    List<Badge> getAllUserBadges(Long userId);

    String awardBadge(AwardBadgeRequest request);

}
