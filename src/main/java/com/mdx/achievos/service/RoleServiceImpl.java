package com.mdx.achievos.service;

import com.mdx.achievos.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Override
    public List<Role> getAllRoles() {
        return List.of();
    }

    @Override
    public Role getRoleById(Long userId) {
        return null;
    }
}
