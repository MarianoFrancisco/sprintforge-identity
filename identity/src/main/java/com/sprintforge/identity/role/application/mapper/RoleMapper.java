package com.sprintforge.identity.role.application.mapper;

import com.sprintforge.identity.permission.domain.Permission;
import com.sprintforge.identity.role.application.port.in.command.CreateRoleCommand;
import com.sprintforge.identity.role.domain.Role;
import lombok.experimental.UtilityClass;

import java.util.Set;

@UtilityClass
public class RoleMapper {
    public Role toDomain(
            CreateRoleCommand command,
            Set<Permission> permission
    ) {
        Role role = new Role(
                command.name(),
                command.description()
        );
        role.setPermissions(permission);
        return role;
    }
}
