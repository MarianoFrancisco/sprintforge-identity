package com.sprintforge.identity.role.infrastructure.adapter.in.rest.dto;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public record RoleResponseDTO(
        UUID id,
        String name,
        String description,
        boolean isDefault,
        boolean isActive,
        boolean isDeleted,
        Instant createdAt,
        Instant updatedAt,
        Set<UUID> permissionIds
) {
}
