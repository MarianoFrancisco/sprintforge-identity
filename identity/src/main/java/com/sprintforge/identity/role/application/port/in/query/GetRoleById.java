package com.sprintforge.identity.role.application.port.in.query;

import com.sprintforge.identity.role.domain.Role;

public interface GetRoleById {
    Role handle(GetRoleByIdQuery query);
}
