package com.mdx.achievos.service.interfaces;

import com.mdx.achievos.dto.request.AwardedBadgeRequest;
import com.mdx.achievos.dto.response.AwardedBadgeResponse;
import com.mdx.achievos.dto.response.LeaderboardResponse;
import com.mdx.achievos.entity.AwardedBadge;

import java.util.List;

public interface AwardedBadgeService {

    List<AwardedBadge> getAllAwardedBadges(Long userId);

    List<AwardedBadge> getAllAwardedBadgesByGrantedBy(Long grantedBy);

    List<AwardedBadgeResponse> getRecentAwardedBadges();

    List<LeaderboardResponse> getLeaderboardByXp();

    AwardedBadgeResponse awardBadge(AwardedBadgeRequest request);


}
