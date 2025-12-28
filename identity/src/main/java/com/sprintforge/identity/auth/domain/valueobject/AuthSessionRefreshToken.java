package com.sprintforge.identity.auth.domain.valueobject;

import com.sprintforge.common.domain.exception.ValidationException;

public record AuthSessionRefreshToken(
        String value
) {
    public AuthSessionRefreshToken {
        if (value == null || value.isBlank()) {
            throw new ValidationException("El token para refrescar sesión no puede estar vacío");
        }

        if (value.length() > 255) {
            throw new ValidationException("El token para refrescar sesión no puede exceder los 255 caracteres");
        }
    }

    public static AuthSessionRefreshToken of(String value) {
        return new AuthSessionRefreshToken(value);
    }
}
