package com.mdx.achievos.service.impl;

import com.mdx.achievos.entity.Role;
import com.mdx.achievos.service.interfaces.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public List<Role> getAllRoles() {
        return List.of();
    }

    @Override
    public Role getRoleById(Long userId) {
        return null;
    }
}
