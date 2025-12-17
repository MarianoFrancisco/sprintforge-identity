package com.sprintforge.identity.user.application.port.in.result;

import java.util.UUID;

public record GetUserAuthDataByEmailResult(
        UUID userId,
        UUID roleId,
        UUID employeeId,
        String email,
        String password,
        String status
) {
}
