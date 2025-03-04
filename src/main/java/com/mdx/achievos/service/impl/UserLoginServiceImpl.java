package com.mdx.achievos.service.impl;

import com.mdx.achievos.dto.request.UserLoginRequest;
import com.mdx.achievos.dto.response.UserLoginResponse;
import com.mdx.achievos.entity.User;
import com.mdx.achievos.exception.BadRequestException;
import com.mdx.achievos.exception.EntityNotFoundException;
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
    public UserLoginResponse loginUser(UserLoginRequest request) {
        if (Objects.isNull(request) || request.getUserEmail() == null || request.getPasswordHash() == null) {
            throw new BadRequestException("Request is empty or missing required fields.");
        }

        User user = userRepo.findByUserEmail(request.getUserEmail())
                .orElseThrow(() -> new EntityNotFoundException("Invalid credentials!"));

        if (!user.getPasswordHash().equals(request.getPasswordHash())) {
            throw new BadRequestException("Invalid credentials!");
        }

        return new UserLoginResponse(
                user.getUserId(),
                user.getUsername(),
                user.getUserEmail()
        );
    }

}
