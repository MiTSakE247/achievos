package com.mdx.achievos.api;

import com.mdx.achievos.dto.ApiResponse;
import com.mdx.achievos.dto.request.UserLoginRequest;
import com.mdx.achievos.dto.response.UserLoginResponse;
import com.mdx.achievos.service.interfaces.UserLoginService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<UserLoginResponse>> login(@RequestBody UserLoginRequest request) {
        UserLoginResponse loginResponse = userLoginService.loginUser(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Login successful", loginResponse));
    }

}
