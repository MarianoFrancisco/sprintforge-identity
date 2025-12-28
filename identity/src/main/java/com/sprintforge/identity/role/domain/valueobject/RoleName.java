package com.sprintforge.identity.role.domain.valueobject;

import com.sprintforge.common.domain.exception.ValidationException;

public record RoleName(
        String value
) {
    public RoleName {
        if (value == null || value.isBlank()) {
            throw new ValidationException("El nombre del rol no puede estar vacío.");
        }
        if (value.length() > 100) {
            throw new ValidationException("El nombre del rol no puede exceder los 100 caracteres.");
        }
    }
}