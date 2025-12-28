package com.sprintforge.identity.role.application.port.in.command;

import java.util.Set;
import java.util.UUID;

public record CreateRoleCommand(
        String name,
        String description,
        Set<UUID> permissionIds
) {
}
