package com.sprintforge.identity.role.application.port.out.persistence;

import com.sprintforge.identity.role.application.port.in.query.GetAllRolesQuery;
import com.sprintforge.identity.role.domain.Role;

import java.util.List;

public interface FindAllRoles {
    List<Role> findAll(GetAllRolesQuery query);
}
