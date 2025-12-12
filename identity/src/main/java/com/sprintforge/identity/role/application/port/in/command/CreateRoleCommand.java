package com.sprintforge.identity.role.application.port.in.command;

public record CreateRoleCommand(
        String name,
        String description
) {
}
