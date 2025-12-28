package com.sprintforge.identity.auth.application.port.in.command;

import java.util.UUID;

public record LogoutCommand(
        UUID authSessionId
) {
}