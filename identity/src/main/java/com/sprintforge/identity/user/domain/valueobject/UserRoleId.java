package com.sprintforge.identity.user.domain.valueobject;

import com.sprintforge.common.domain.exception.ValidationException;

import java.util.UUID;

public record UserRoleId(
        UUID value
) {
    public UserRoleId {
        if (value == null) {
            throw new ValidationException("El rol de usuario no puede estar vacío");
        }
    }
}
