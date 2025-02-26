package com.mdx.achievos.achievos.service;

import com.mdx.achievos.achievos.dto.UserAccountRequest;
import com.mdx.achievos.achievos.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    String addUser(UserAccountRequest userAccountRequest);
}
