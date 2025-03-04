package com.mdx.achievos.service.impl;

import com.mdx.achievos.dto.request.LevelRequest;
import com.mdx.achievos.entity.Role;
import com.mdx.achievos.entity.User;
import com.mdx.achievos.service.interfaces.AdminService;
import com.mdx.achievos.service.interfaces.LevelService;
import com.mdx.achievos.service.interfaces.RoleService;
import com.mdx.achievos.service.interfaces.UserService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final LevelService levelService;
    private final RoleService roleService;
    private final UserService userService;

    public AdminServiceImpl(LevelService levelService, RoleService roleService, UserService userService) {
        this.levelService = levelService;
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    public String getAllLevels(Long userId, LevelRequest request) {
        if(request == null) {
            return "Request is empty!";
        }

        User user = userService.getUserById(userId);

        if (user == null) {
            return "User not found";
        }

        Long roleId = user.getRoleId();

        Role role = roleService.getRoleById(userId);

        if (role.getRoleName().getLevel() >= 4) {
            return levelService.addLevel(request);
        }
        return "";
    }
}
