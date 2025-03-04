package com.mdx.achievos.service.interfaces;

import com.mdx.achievos.dto.request.UserAccountRequest;
import com.mdx.achievos.dto.request.UserLoginRequest;
import com.mdx.achievos.entity.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    UserAccountRequest addUser(UserAccountRequest request);

    UserAccountRequest updateUser(Long userId, UserAccountRequest request);

    UserAccountRequest patchUser(Long userId, UserAccountRequest request);

    User getUserById(Long userId);

    User getUserByCred(@RequestBody UserLoginRequest request);
}
