package com.sprintforge.identity.role.infrastructure.adapter.in.rest.dto;

import java.util.UUID;

public record RolePermissionDTO(
        UUID id,
        String code,
        String name,
        String category
) {
}
