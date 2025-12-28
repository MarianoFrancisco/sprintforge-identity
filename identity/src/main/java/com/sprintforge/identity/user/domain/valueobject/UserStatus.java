package com.sprintforge.identity.user.domain.valueobject;

import com.sprintforge.common.domain.exception.ValidationException;

public enum UserStatus {
    ACTIVE,
    LOCKED,
    DISABLED,
    PENDING_ACTIVATION;

    public static UserStatus safeValueOf(String value) {
        if (value == null) {
            throw new ValidationException("El estado del usuario no puede estar vacío");
        }
        try {
            return UserStatus.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new ValidationException("El estado del usuario es inválido: " + value);
        }
    }
}