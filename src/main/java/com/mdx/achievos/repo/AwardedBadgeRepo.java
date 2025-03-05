package com.mdx.achievos.repo;

import com.mdx.achievos.dto.response.LeaderboardResponse;
import com.mdx.achievos.entity.AwardedBadge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AwardedBadgeRepo extends JpaRepository<AwardedBadge, Long> {

    public List<AwardedBadge> findAllUserBadgeByUserId(Long userId);

    public List<AwardedBadge> findAllUserBadgeByGrantedBy(Long grantedBy);

    public List<AwardedBadge> findTop50ByOrderByEarnedAtDesc();

    @Query(value = "SELECT u.user_id, u.username, SUM(b.badge_xp) AS totalXp " +
            "FROM awarded_badges ab " +
            "JOIN badges b ON ab.badge_id = b.badge_id " +
            "JOIN users u ON ab.user_id = u.user_id " +
            "GROUP BY u.user_id, u.username " +
            "ORDER BY totalXp DESC",
            nativeQuery = true)
    List<Object[]> getLeaderboardByXpNative();


}
