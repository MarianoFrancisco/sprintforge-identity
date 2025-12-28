package com.sprintforge.identity.role.domain.valueobject;

import com.sprintforge.common.domain.exception.ValidationException;

import java.util.UUID;

public record RoleId(
        UUID value
) {
    public RoleId {
        if (value == null) {
            throw new ValidationException("El identificador de rol no puede estar vacío.");
        }
    }
}