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
    public String addUser(@RequestBody UserAccountRequest request) {
        return userService.addUser(request);
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody UserAccountRequest request) {
        return userService.updateUser(id, request);
    }

    @PatchMapping("/{id}")
    public String updatePassword(@PathVariable Long id, @RequestBody UserAccountRequest request) {
        return userService.patchUser(id, request);
    }
}
