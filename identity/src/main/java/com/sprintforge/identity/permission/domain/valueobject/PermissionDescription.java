package com.sprintforge.identity.permission.domain.valueobject;

public record PermissionDescription(String value) {

    public PermissionDescription {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        }
        if (value.length() > 255) {
            throw new IllegalArgumentException("La descripción no puede exceder los 255 caracteres");
        }
    }
}