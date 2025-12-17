package com.sprintforge.identity.auth.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record LogoutRequestDTO(
        @NotNull(message = "El identificador de sesión es obligatorio")
        UUID authSessionId
) {
}
