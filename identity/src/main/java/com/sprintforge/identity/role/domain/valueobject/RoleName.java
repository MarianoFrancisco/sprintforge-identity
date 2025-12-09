package com.sprintforge.identity.role.domain.valueobject;

public record RoleName(
        String value
) {
    public RoleName {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El nombre del rol no puede estar vacío.");
        }
        if (value.length() > 100) {
            throw new IllegalArgumentException("El nombre del rol no puede exceder los 100 caracteres.");
        }
    }
}