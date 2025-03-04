package com.mdx.achievos.repo;

import com.mdx.achievos.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgeRepo extends JpaRepository<Badge, Long> {
    boolean existsByBadgeName(String badgeName);
}
