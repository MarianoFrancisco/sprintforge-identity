package com.sprintforge.identity.auth.domain.valueobject;

import com.sprintforge.common.domain.exception.ValidationException;

public record AuthSessionUserAgent(
        String value
) {
    public AuthSessionUserAgent {
        if (value != null && value.length() > 255) {
            throw new ValidationException("El agente de usuario no puede exceder los 255 caracteres");
        }
    }

    public static AuthSessionUserAgent of(String value) {
        return new AuthSessionUserAgent(value);
    }
}