package com.sprintforge.identity.permission.application.port.in.query;

import com.sprintforge.identity.permission.domain.Permission;

import java.util.Set;

public interface GetPermissionsByIdIn {
    Set<Permission> handle(GetPermissionsByIdInQuery query);
}
