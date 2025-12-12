package com.sprintforge.identity.user.domain.valueobject;

public record UserEmail(
        String value
) {
    public UserEmail {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El correo electrónico no puede estar vacío");
        }
        if (!value.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("El correo electrónico no es válido");
        }
    }
}