package com.sprintforge.identity.user.application.port.in.command;

import java.util.UUID;

public record UpdateUserPasswordCommand(
        UUID id,
        String newPassword
) {
}
