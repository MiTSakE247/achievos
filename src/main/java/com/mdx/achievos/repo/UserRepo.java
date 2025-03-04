package com.mdx.achievos.repo;

import com.mdx.achievos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    public Optional<User> findByUsername(String username);

    public Optional<User> findByUserEmail(String userEmail);

}
