package com.mdx.achievos.service;

import com.mdx.achievos.dto.AwardBadgeRequest;
import com.mdx.achievos.entity.UserBadge;

import java.util.List;

public interface UserBadgeService {

    List<UserBadge> getAllUserBadges(Long userId);

    List<UserBadge> getAllUserBadgesByGrantedBy(Long grantedBy);

    String awardBadge(AwardBadgeRequest request);

}
