package com.mdx.achievos.service.interfaces;

import com.mdx.achievos.dto.AwardBadgeRequest;
import com.mdx.achievos.entity.AwardedBadge;

import java.util.List;

public interface AwardedBadgeService {

    List<AwardedBadge> getAllUserBadges(Long userId);

    List<AwardedBadge> getAllUserBadgesByGrantedBy(Long grantedBy);

    String awardBadge(AwardBadgeRequest request);

}
