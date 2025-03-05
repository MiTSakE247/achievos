package com.mdx.achievos.service.impl;

import com.mdx.achievos.dto.request.UserAccountRequest;
import com.mdx.achievos.dto.request.UserLoginRequest;
import com.mdx.achievos.entity.User;
import com.mdx.achievos.exception.BadRequestException;
import com.mdx.achievos.exception.DuplicateResourceException;
import com.mdx.achievos.exception.EntityNotFoundException;
import com.mdx.achievos.repo.UserRepo;
import com.mdx.achievos.service.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.mdx.achievos.util.AppUtility.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public UserAccountRequest addUser(UserAccountRequest request) {
        if (Objects.isNull(request)) {
            throw new BadRequestException("Request body is empty.");
        }

        if (!isValidEmail(request.getUserEmail())) {
            throw new BadRequestException("Email is not valid.");
        }

        if (isEmpty(request.getUsername()) || isEmpty(request.getUserEmail()) || isEmpty(request.getPasswordHash())) {
            throw new BadRequestException("Required fields are missing or empty.");
        }

        if (userRepo.findByUsername(request.getUsername()).isPresent() ||
                userRepo.findByUserEmail(request.getUserEmail()).isPresent()) {
            throw new DuplicateResourceException("Account with this username or email already exists.");
        }

        User user = createUser(request);
        userRepo.save(user);

        UserAccountRequest response = new UserAccountRequest();
        response.setName(user.getName());
        response.setUserEmail(user.getUserEmail());
        response.setProfilePic(user.getProfilePic());
        response.setBio(user.getBio());

        return response;
    }

    @Override
    public UserAccountRequest updateUser(Long userId, UserAccountRequest request) {
        if (Objects.isNull(request)) {
            throw new BadRequestException("Request body is empty.");
        }

        if (isEmpty(request.getUsername()) || isEmpty(request.getUserEmail()) || isEmpty(request.getPasswordHash())) {
            throw new BadRequestException("Required fields are missing or empty.");
        }

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        // Update user fields if provided
        if (request.getName() != null) {
            user.setName(request.getName());
        }

        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }

        if (request.getProfilePic() != null) {
            user.setProfilePic(request.getProfilePic());
        }

        if (request.getBio() != null) {
            user.setBio(request.getBio());
        }

        // Save updated user
        userRepo.save(user);

        // Return updated details as DTO
        return new UserAccountRequest(
                user.getName(),
                user.getUsername(),
                user.getUserEmail(),
                null, // Do not return password hash for security reasons
                null, // newPasswordHash should be null
                user.getProfilePic(),
                user.getBio()
        );
    }

    @Override
    public UserAccountRequest patchUser(Long userId, UserAccountRequest request) {
        if (Objects.isNull(request)) {
            throw new BadRequestException("Request body is empty.");
        }

        if (isEmpty(request.getUsername()) || isEmpty(request.getUserEmail()) || isEmpty(request.getPasswordHash())) {
            throw new BadRequestException("Required fields are missing or empty.");
        }

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        // Check if username needs updating
        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }

        // Handle password update correctly
        if (request.getPasswordHash() != null && request.getNewPasswordHash() != null) {
            if (!user.getPasswordHash().equals(request.getPasswordHash())) {
                throw new BadRequestException("Current password is incorrect.");
            }
            user.setPasswordHash(request.getNewPasswordHash());
        }

        // Save updated user
        userRepo.save(user);

        // Return updated user as DTO
        return new UserAccountRequest(
                user.getName(),
                user.getUsername(),
                user.getUserEmail(),
                null, // Hide password for security
                null, // Hide new password
                user.getProfilePic(),
                user.getBio()
        );
    }

    @Override
    public User getUserById(Long userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
    }

    @Override
    public User getUserByCred(UserLoginRequest request) {
        return userRepo.findByUserEmail(request.getUserEmail())
                .filter(user -> user.getPasswordHash().equals(request.getPasswordHash()))
                .orElseThrow(() -> new EntityNotFoundException("Invalid credentials!"));
    }

    private User createUser(UserAccountRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setUserEmail(request.getUserEmail());
        user.setPasswordHash(request.getPasswordHash());
        // role & level can be assigned by admin
        return user;
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

}
