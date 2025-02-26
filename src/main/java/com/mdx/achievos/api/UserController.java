package com.mdx.achievos.api;

import com.mdx.achievos.dto.UserAccountRequest;
import com.mdx.achievos.entity.User;
import com.mdx.achievos.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public String addUser(@RequestBody UserAccountRequest userAccountRequest) {
        return userService.addUser(userAccountRequest);
    }
}
