package com.sprintforge.identity.permission.domain.valueobject;

import com.sprintforge.common.domain.exception.ValidationException;

public record PermissionName(String value) {

    public PermissionName {
        if (value == null || value.isBlank()) {
            throw new ValidationException("El nombre no puede estar vacío");
        }
        if (value.length() > 50) {
            throw new ValidationException("El nombre no puede exceder los 50 caracteres");
        }
    }
}