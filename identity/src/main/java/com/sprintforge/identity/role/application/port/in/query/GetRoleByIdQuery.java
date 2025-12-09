package com.sprintforge.identity.role.application.port.in.query;

import java.util.UUID;

public record GetRoleByIdQuery(
        UUID id
) {
}
