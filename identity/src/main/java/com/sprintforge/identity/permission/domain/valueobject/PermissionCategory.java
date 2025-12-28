package com.sprintforge.identity.permission.domain.valueobject;

import com.sprintforge.common.domain.exception.ValidationException;

public record PermissionCategory(String value) {

    public PermissionCategory {
        if (value == null || value.isBlank()) {
            throw new ValidationException("La categoría no puede estar vacía");
        }
        if (value.length() > 50) {
            throw new ValidationException("La categoría no puede exceder los 50 caracteres");
        }
    }
}