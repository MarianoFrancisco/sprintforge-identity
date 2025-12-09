package com.sprintforge.identity.role.application.port.in.command;

import java.util.UUID;

public record ActivateRoleCommand(
        UUID id
) {
}
