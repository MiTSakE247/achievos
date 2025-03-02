package com.mdx.achievos.service;

import com.mdx.achievos.dto.UserLoginRequest;

public interface UserLoginService {
    String loginUser(UserLoginRequest request);
}
