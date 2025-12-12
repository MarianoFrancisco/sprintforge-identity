package com.sprintforge.identity.user.domain.valueobject;

import java.util.UUID;

public record UserId(
        UUID value
) {
    public UserId {
        if (value == null) {
            throw new IllegalArgumentException("El identificador de usuario no puede estar vacío");
        }
    }
}