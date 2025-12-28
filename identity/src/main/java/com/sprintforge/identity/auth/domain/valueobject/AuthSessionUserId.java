package com.sprintforge.identity.auth.domain.valueobject;

import com.sprintforge.common.domain.exception.ValidationException;

import java.util.UUID;

public record AuthSessionUserId(
        UUID value
) {
    public AuthSessionUserId {
        if (value == null) {
            throw new ValidationException("El identificador de usuario de la sesión de autenticación no puede estar vacío");
        }
    }

    public static AuthSessionUserId of(UUID value) {
        return new AuthSessionUserId(value);
    }
}
