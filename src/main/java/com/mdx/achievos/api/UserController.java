package com.mdx.achievos.api;

import com.mdx.achievos.dto.ApiResponse;
import com.mdx.achievos.dto.request.UserAccountRequest;
import com.mdx.achievos.dto.request.UserLoginRequest;
import com.mdx.achievos.entity.User;
import com.mdx.achievos.service.interfaces.UserService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ApiResponse<List<User>>> getUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(new ApiResponse<>(true, "Users retrieved successfully", users));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserAccountRequest>> addUser(@RequestBody UserAccountRequest request) {
        UserAccountRequest user = userService.addUser(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "User created successfully", user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserAccountRequest>> updateUser(@PathVariable("id") Long id, @RequestBody UserAccountRequest request) {
        UserAccountRequest updatedUser = userService.updateUser(id, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "User updated successfully", updatedUser));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<UserAccountRequest>> updatePassword(@PathVariable("id") Long id, @RequestBody UserAccountRequest request) {
        UserAccountRequest updatedPassword = userService.patchUser(id, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Password updated successfully", updatedPassword));
    }

    // will delete this
    @PostMapping("/id")
    public ResponseEntity<ApiResponse<User>> getUsersByCreds(@RequestBody UserLoginRequest request) {
        User user = userService.getUserByCred(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "User retrieved successfully", user));
    }

}
