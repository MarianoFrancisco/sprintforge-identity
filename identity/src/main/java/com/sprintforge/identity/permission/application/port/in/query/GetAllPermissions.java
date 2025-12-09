package com.sprintforge.identity.permission.application.port.in.query;

import com.sprintforge.identity.permission.domain.Permission;

import java.util.List;

public interface GetAllPermissions {
    List<Permission> handle(GetAllPermissionsQuery query);
}
