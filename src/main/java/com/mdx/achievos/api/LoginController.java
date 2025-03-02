package com.mdx.achievos.api;

import com.mdx.achievos.dto.UserLoginRequest;
import com.mdx.achievos.service.UserLoginService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final UserLoginService userLoginService;

    public LoginController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @PostMapping
    public String login(@RequestBody UserLoginRequest request) {
        return userLoginService.loginUser(request);
    }
}
