package com.mdx.achievos.entity;

import com.mdx.achievos.entity.enums.UserRoles;
import jakarta.persistence.*;

@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    private Enum<UserRoles> roleName;
    private String roleBadge;
    private String roleDescription;
    private Integer roleLevel;

    public Role() {

    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Enum<UserRoles> getRoleName() {
        return roleName;
    }

    public void setRoleName(Enum<UserRoles> roleName) {
        this.roleName = roleName;
    }

    public String getRoleBadge() {
        return roleBadge;
    }

    public void setRoleBadge(String roleBadge) {
        this.roleBadge = roleBadge;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleBadge='" + roleBadge + '\'' +
                ", roleDescription='" + roleDescription + '\'' +
                ", roleLevel=" + roleLevel +
                '}';
    }
}
