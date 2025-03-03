package com.mdx.achievos.repo;

import com.mdx.achievos.entity.UserBadge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBadgeRepo extends JpaRepository<UserBadge, Long> {

    public List<UserBadge> findAllUserBadgeByUserId(Long userId);

    public List<UserBadge> findAllUserBadgeByGrantedBy(Long grantedBy);

}
