package com.sprintforge.identity.user.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateUserRoleRequestDTO(
        @NotNull(message = "El rol del usuario no puede estar vacío")
        UUID roleId
) {
}
