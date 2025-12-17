package com.sprintforge.identity.auth.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record RefreshTokenRequestDTO(
        @NotNull(message = "El identificador de sesión de autenticación es obligatorio")
        UUID authSessionId,
        @NotBlank(message = "El token para refrescar sesión es obligatorio")
        @Size(max = 255, message = "El token para refrescar sesión no puede exceder los 255 caracteres")
        String refreshToken
) {
}
