package com.mdx.achievos.service;

import com.mdx.achievos.dto.UserAccountRequest;
import com.mdx.achievos.entity.User;
import com.mdx.achievos.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.mdx.achievos.util.AppUtility.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public String addUser(UserAccountRequest request) {
        if (Objects.isNull(request)) {
            return "Request body is empty";
        }

        // check for standard input otherwise return
        if (!isValidEmail(request.getUserEmail())) {
            return "Email is not valid";
        }

        // check is user already exists by the column - username, email
        if (userRepo.findByUsername(request.getUsername()) != null ||
                userRepo.findByUserEmail(request.getUserEmail()) != null) {
            return "Account already exists";
        }

        User user = createUser(request);
        userRepo.save(user);
        return "User saved successfully";
    }

    @Override
    public String updateUser(Long userId, UserAccountRequest request) {
        if (Objects.isNull(request)) {
            return "Request is empty";
        }

        Optional<User> optionalUser = userRepo.findById(userId);

        if (optionalUser.isEmpty()) {
            return "User not found!";
        }

        User user = optionalUser.get();
        String fields = "[";

        // update the provided details except email, password, userStatus, role for now
        if (request.getName() != null) {
            user.setName(request.getName());
            fields += "Name, ";
        }

        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
            fields += "Username, ";
        }

        if (request.getProfilePic() != null) {
            user.setProfilePic(request.getProfilePic());
            fields += "Profile Pic, ";
        }

        if (request.getBio() != null) {
            user.setBio(request.getBio());
            fields += "Bio";
        }

        userRepo.save(user);


        return "User updated successfully. Updated fields: " + fields + "]";
    }

    @Override
    public String patchUser(Long userId, UserAccountRequest request) {
        if (Objects.isNull(request)) {
            return "Request is empty";
        }

        Optional<User> optionalUser = userRepo.findById(userId);

        if (optionalUser.isEmpty()) {
            return "User not found!";
        }

        User user = optionalUser.get();

        // check is User wants to update username
        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
            userRepo.save(user);
            return "Username successfully updated";
        }

        String currentPassword = "", newPassword = "";

        if (request.getPasswordHash() != null) {
            currentPassword = request.getPasswordHash();
        }

        if (request.getNewPasswordHash() != null) {
            newPassword = request.getNewPasswordHash();
        }

        if (!currentPassword.equals(newPassword)) {
            return "Current password doesn't match!";
        }

        user.setPasswordHash(newPassword);
        userRepo.save(user);

        return "Password successfully updated";
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

}
