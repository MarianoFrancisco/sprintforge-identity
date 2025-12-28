package com.sprintforge.identity.user.application.port.in.command;

import java.util.UUID;

public record SetInitialUserPasswordCommand(
        UUID id,
        String initialPassword
) {
}
