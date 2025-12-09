package com.sprintforge.identity.role.domain.valueobject;

public record RoleDescription(
        String value
) {
    public RoleDescription {
        if (value != null && value.length() > 250) {
            throw new IllegalArgumentException("La descripción del rol no puede exceder los 250 caracteres.");
        }
    }
}
