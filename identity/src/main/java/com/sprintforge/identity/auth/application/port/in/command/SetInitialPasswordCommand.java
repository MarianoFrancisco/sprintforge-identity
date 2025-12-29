package com.sprintforge.identity.auth.application.port.in.command;

import java.util.UUID;

public record SetInitialPasswordCommand(
        UUID userId,
        String initialPassword
) {
}
