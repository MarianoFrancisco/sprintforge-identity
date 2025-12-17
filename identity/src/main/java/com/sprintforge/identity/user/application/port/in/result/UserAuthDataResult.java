package com.sprintforge.identity.user.application.port.in.result;

import java.util.UUID;

public record UserAuthDataResult(
        UUID userId,
        UUID roleId,
        UUID employeeId,
        String email,
        String password,
        String status
) {
}
