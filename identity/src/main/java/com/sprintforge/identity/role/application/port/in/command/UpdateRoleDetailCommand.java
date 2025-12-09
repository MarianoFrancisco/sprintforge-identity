package com.sprintforge.identity.role.application.port.in.command;

import java.util.UUID;

public record UpdateRoleDetailCommand(
        UUID id,
        String name,
        String description,
        boolean isDefault
) {
}
