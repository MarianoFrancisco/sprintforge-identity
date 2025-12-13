package com.sprintforge.identity.permission.domain.valueobject;

import com.sprintforge.common.domain.exception.ValidationException;

public record PermissionDescription(String value) {

    public PermissionDescription {
        if (value == null || value.isBlank()) {
            throw new ValidationException("La descripción no puede estar vacía");
        }
        if (value.length() > 255) {
            throw new ValidationException("La descripción no puede exceder los 255 caracteres");
        }
    }
}