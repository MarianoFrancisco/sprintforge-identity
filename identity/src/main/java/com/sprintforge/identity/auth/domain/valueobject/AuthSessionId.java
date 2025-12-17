package com.sprintforge.identity.auth.domain.valueobject;

import com.sprintforge.common.domain.exception.ValidationException;

import java.util.UUID;

import static java.util.UUID.randomUUID;

public record AuthSessionId(
        UUID value
) {
    public AuthSessionId {
        if (value == null) {
            throw new ValidationException("El identificador de la sesión de autenticación no puede estar vacío");
        }
    }

    public static AuthSessionId generate() {
        return new AuthSessionId(randomUUID());
    }

    public static AuthSessionId of(UUID value) {
        return new AuthSessionId(value);
    }
}
