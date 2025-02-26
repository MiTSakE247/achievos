package com.mdx.achievos.repo;

import com.mdx.achievos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    public User findByUsername(String username);

    public User findByUserEmail(String userEmail);

}
