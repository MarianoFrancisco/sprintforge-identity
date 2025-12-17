package com.sprintforge.identity.auth.domain.valueobject;

import com.sprintforge.common.domain.exception.ValidationException;

public record AuthSessionRevokedReason(
        String value
) {
    public AuthSessionRevokedReason {
        if (value != null && value.length() > 255) {
            throw new ValidationException("El motivo de revocación no puede exceder los 255 caracteres");
        }
    }

    public static AuthSessionRevokedReason of(String value) {
        return new AuthSessionRevokedReason(value);
    }
}
