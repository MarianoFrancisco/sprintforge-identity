package com.sprintforge.identity.permission.application.port.out.persistence;

import com.sprintforge.identity.permission.application.port.in.query.GetAllPermissionsQuery;
import com.sprintforge.identity.permission.domain.Permission;

import java.util.List;

public interface FindAllPermissions {
    List<Permission> findAll(GetAllPermissionsQuery query);
}
