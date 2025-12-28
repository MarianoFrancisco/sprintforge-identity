package com.sprintforge.identity.role.application.port.in.command;

import com.sprintforge.identity.role.domain.Role;

public interface CreateRole {
    Role handle(CreateRoleCommand command);
}
