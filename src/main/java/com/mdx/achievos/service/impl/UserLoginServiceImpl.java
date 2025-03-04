package com.mdx.achievos.service.impl;

import com.mdx.achievos.dto.UserLoginRequest;
import com.mdx.achievos.entity.User;
import com.mdx.achievos.repo.UserRepo;
import com.mdx.achievos.service.interfaces.UserLoginService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    private final UserRepo userRepo;

    public UserLoginServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public String loginUser(UserLoginRequest request) {
        if(Objects.isNull(request)) {
            return "Request is Empty!";
        }
        String userEmail = request.getUserEmail();
        String passwordHash = request.getPasswordHash();

        User user = userRepo.findByUserEmail(userEmail);
        if(user.getPasswordHash().equals(passwordHash)) {
            return "AUTHENTICATION_SUCCESS:" + passwordHash + ":" + user.getUserId();
        }
        return "AUTHENTICATION_FAILED";
    }
}
