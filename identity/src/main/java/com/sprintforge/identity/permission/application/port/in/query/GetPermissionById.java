package com.sprintforge.identity.permission.application.port.in.query;

import com.sprintforge.identity.permission.domain.Permission;

public interface GetPermissionById {
    Permission handle(GetPermissionByIdQuery query);
}
