package com.mdx.achievos.service.interfaces;

import com.mdx.achievos.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    Role getRoleById(Long userId);

}
