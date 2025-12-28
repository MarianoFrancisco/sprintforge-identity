package com.sprintforge.identity.role.infrastructure.adapter.in.rest.dto;

import com.sprintforge.identity.permission.infrastructure.adapter.in.rest.dto.PermissionResponseDTO;

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
        Set<PermissionResponseDTO> permissions
) {
}
