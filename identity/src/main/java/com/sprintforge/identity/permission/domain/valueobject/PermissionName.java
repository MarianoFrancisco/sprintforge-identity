package com.sprintforge.identity.permission.domain.valueobject;

public record PermissionName(String value) {

    public PermissionName {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (value.length() > 50) {
            throw new IllegalArgumentException("El nombre no puede exceder los 50 caracteres");
        }
    }
}