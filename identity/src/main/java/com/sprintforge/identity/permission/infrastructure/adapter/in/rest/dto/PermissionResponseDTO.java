package com.sprintforge.identity.permission.infrastructure.adapter.in.rest.dto;

import java.util.UUID;

public record PermissionResponseDTO(
        UUID id,
        String code,
        String name,
        String description,
        String category
) {
}
