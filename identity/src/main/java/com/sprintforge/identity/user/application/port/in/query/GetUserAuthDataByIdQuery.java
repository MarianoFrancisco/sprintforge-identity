package com.sprintforge.identity.user.application.port.in.query;

import java.util.UUID;

public record GetUserAuthDataByIdQuery(
        UUID id
) {
}
