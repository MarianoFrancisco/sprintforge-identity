package com.sprintforge.identity.user.domain.valueobject;

import com.sprintforge.common.domain.exception.ValidationException;

import java.util.UUID;

public record UserId(
        UUID value
) {
    public UserId {
        if (value == null) {
            throw new ValidationException("El identificador de usuario no puede estar vacío");
        }
    }
}