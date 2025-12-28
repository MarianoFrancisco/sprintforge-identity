package com.sprintforge.identity.user.domain.valueobject;

import com.sprintforge.common.domain.exception.ValidationException;

import java.util.UUID;

public record UserEmployeeId(
        UUID value
) {
    public UserEmployeeId {
        if (value == null) {
            throw new ValidationException("El identificador de empleado no puede estar vacío");
        }
    }
}