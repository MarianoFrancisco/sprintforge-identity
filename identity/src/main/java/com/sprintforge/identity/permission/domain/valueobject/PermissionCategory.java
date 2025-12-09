package com.sprintforge.identity.permission.domain.valueobject;

public record PermissionCategory(String value) {

    public PermissionCategory {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("La categoría no puede estar vacía");
        }
        if (value.length() > 50) {
            throw new IllegalArgumentException("La categoría no puede exceder los 50 caracteres");
        }
    }
}