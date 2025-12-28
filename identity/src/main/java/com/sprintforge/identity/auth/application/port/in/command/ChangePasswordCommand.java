package com.sprintforge.identity.auth.application.port.in.command;

import java.util.UUID;

public record ChangePasswordCommand(
        UUID userId,
        String currentPassword,
        String newPassword
) {
}