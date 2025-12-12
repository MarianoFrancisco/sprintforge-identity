package com.sprintforge.identity.user.domain.valueobject;

public record UserPassword(
        String value
) {
    public UserPassword {
        if (value != null && value.length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
        }
    }
}