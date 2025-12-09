package com.sprintforge.identity.permission.application.port.in.query;

import java.util.UUID;

public record GetPermissionByIdQuery(
        UUID id
) {
}
