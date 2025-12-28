package com.sprintforge.identity.role.application.port.in.query;

import com.sprintforge.identity.role.domain.Role;

import java.util.List;

public interface GetAllRoles {
    List<Role> handle(GetAllRolesQuery query);
}
