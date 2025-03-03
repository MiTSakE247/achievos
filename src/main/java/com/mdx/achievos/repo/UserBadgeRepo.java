package com.mdx.achievos.repo;

import com.mdx.achievos.entity.AwardedBadge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBadgeRepo extends JpaRepository<AwardedBadge, Long> {

    public List<AwardedBadge> findAllUserBadgeByUserId(Long userId);

    public List<AwardedBadge> findAllUserBadgeByGrantedBy(Long grantedBy);

}
