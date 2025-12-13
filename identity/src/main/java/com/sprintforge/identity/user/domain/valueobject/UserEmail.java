package com.sprintforge.identity.user.domain.valueobject;

import com.sprintforge.common.domain.exception.ValidationException;

public record UserEmail(
        String value
) {
    public UserEmail {
        if (value == null || value.isBlank()) {
            throw new ValidationException("El correo electrónico no puede estar vacío");
        }
        if (!value.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new ValidationException("El correo electrónico no es válido");
        }
    }
}