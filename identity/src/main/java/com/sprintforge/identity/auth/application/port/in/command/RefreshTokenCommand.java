package com.sprintforge.identity.auth.application.port.in.command;

public record RefreshTokenCommand(
        String refreshToken,
        String userAgent,
        String ipAddress
) {
}
