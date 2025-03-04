package com.mdx.achievos.service.interfaces;

import com.mdx.achievos.dto.request.UserLoginRequest;
import com.mdx.achievos.dto.response.UserLoginResponse;

public interface UserLoginService {
    UserLoginResponse loginUser(UserLoginRequest request);
}
