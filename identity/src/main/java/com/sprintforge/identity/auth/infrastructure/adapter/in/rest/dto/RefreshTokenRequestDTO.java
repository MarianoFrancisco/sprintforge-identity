package com.sprintforge.identity.auth.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RefreshTokenRequestDTO(
        @NotBlank(message = "El token para refrescar sesión es obligatorio")
        @Size(max = 255, message = "El token para refrescar sesión no puede exceder los 255 caracteres")
        String refreshToken
) {
}
