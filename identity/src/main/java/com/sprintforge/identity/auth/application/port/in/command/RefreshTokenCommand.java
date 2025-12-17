package com.sprintforge.identity.auth.application.port.in.command;

import java.util.UUID;

public record RefreshTokenCommand(
        UUID authSessionId,
        String refreshToken,
        String userAgent,
        String ipAddress
) {
}
