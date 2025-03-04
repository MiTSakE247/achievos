package com.mdx.achievos.service.interfaces;

import com.mdx.achievos.dto.request.AwardedBadgeRequest;
import com.mdx.achievos.dto.response.AwardedBadgeResponse;
import com.mdx.achievos.entity.AwardedBadge;

import java.util.List;

public interface AwardedBadgeService {

    List<AwardedBadge> getAllUserBadges(Long userId);

    List<AwardedBadge> getAllUserBadgesByGrantedBy(Long grantedBy);

    AwardedBadgeResponse awardBadge(AwardedBadgeRequest request);

}
