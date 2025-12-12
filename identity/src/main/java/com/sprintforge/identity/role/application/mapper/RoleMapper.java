package com.sprintforge.identity.role.application.mapper;

import com.sprintforge.identity.role.application.port.in.command.CreateRoleCommand;
import com.sprintforge.identity.role.domain.Role;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RoleMapper {
    public Role toDomain(
            CreateRoleCommand command
    ) {
        Role role = new Role(
                command.name(),
                command.description()
        );
        role.setPermissions(command.permissionIds());
        return role;
    }
}
