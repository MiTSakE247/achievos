package com.mdx.achievos.entity;

import com.mdx.achievos.entity.enums.UserRoles;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Enumerated(EnumType.STRING)
    private UserRoles roleName;

    private String roleBadge;
    private String roleDescription;
}
