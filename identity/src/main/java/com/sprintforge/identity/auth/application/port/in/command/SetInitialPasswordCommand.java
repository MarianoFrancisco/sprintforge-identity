package com.sprintforge.identity.auth.application.port.in.command;

import java.util.UUID;

public record SetInitialPasswordCommand(
        String token,
        UUID userId,
        String initialPassword
) {
}
