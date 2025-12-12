package com.sprintforge.identity.user.domain.valueobject;

public enum UserStatus {
    ACTIVE,
    LOCKED,
    DISABLED,
    PENDING_ACTIVATION;

    public static UserStatus safeValueOf(String value) {
        if (value == null) {
            throw new IllegalArgumentException("El estado del usuario no puede estar vacío");
        }
        try {
            return UserStatus.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("El estado del usuario es inválido: " + value);
        }
    }
}