package com.sprintforge.identity.user.domain.valueobject;

import java.util.UUID;

public record UserEmployeeId(
        UUID value
) {
    public UserEmployeeId {
        if (value == null) {
            throw new IllegalArgumentException("El identificador de empleado no puede estar vacío");
        }
    }
}