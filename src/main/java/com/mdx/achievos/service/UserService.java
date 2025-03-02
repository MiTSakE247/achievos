package com.mdx.achievos.service;

import com.mdx.achievos.dto.UserAccountRequest;
import com.mdx.achievos.dto.UserLoginRequest;
import com.mdx.achievos.entity.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    String addUser(UserAccountRequest request);

    String updateUser(Long userId, UserAccountRequest request);

    String patchUser(Long userId, UserAccountRequest request);

    User getUserById(Long userId);

    User getUserByCred(@RequestBody UserLoginRequest request);
}
