package com.sprintforge.identity.role.infrastructure.adapter.in.rest.dto;

public record UpdateRoleRequestDTO(
        String name,
        String description,
        Boolean isDefault
) {
}
