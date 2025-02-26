package com.mdx.achievos.service;

import com.mdx.achievos.dto.UserAccountRequest;
import com.mdx.achievos.entity.User;
import com.mdx.achievos.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    public String addUser(UserAccountRequest userAccountRequest) {
        if (Objects.isNull(userAccountRequest))
            return "Request body is empty";

        // check for standard input otherwise return
        if (!isValidEmail(userAccountRequest.getUserEmail())) return "Email is not valid";

        // check is user already exists by the column - username, email
        if (userRepo.findByUsername(userAccountRequest.getUsername()) != null ||
                userRepo.findByUserEmail(userAccountRequest.getUserEmail()) != null)
            return "Account already exists";

        User user = getUser(userAccountRequest);
        userRepo.save(user);
        return "User saved successfully";
    }

    private User getUser(UserAccountRequest userAccountRequest) {
        User user = new User();
        user.setName(userAccountRequest.getName());
        user.setUsername(userAccountRequest.getUsername());
        user.setUserEmail(userAccountRequest.getUserEmail());
        user.setPasswordHash(userAccountRequest.getPasswordHash());
        // role & level can be assigned by admin
        return user;
    }
}
