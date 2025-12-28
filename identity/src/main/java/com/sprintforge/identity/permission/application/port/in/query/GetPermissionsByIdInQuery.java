package com.sprintforge.identity.permission.application.port.in.query;

import java.util.Set;
import java.util.UUID;

public record GetPermissionsByIdInQuery(
        Set<UUID> ids
) {
}
