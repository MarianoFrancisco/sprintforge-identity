package com.sprintforge.identity.auth.application.port.in.command;

public record LoginCommand(
        String email,
        String password,
        String userAgent,
        String ipAddress
) {
}