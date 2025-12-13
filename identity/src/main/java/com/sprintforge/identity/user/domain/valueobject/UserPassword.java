package com.sprintforge.identity.user.domain.valueobject;

import com.sprintforge.common.domain.exception.ValidationException;

public record UserPassword(
        String value
) {
    public UserPassword {
        if (value != null && value.length() < 8) {
            throw new ValidationException("La contraseña debe tener al menos 8 caracteres");
        }
    }
}