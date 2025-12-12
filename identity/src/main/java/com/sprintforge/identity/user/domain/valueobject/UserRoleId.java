package com.sprintforge.identity.user.domain.valueobject;

import java.util.UUID;

public record UserRoleId(
        UUID value
) {
    public UserRoleId {
        if (value == null) {
            throw new IllegalArgumentException("El rol de usuario no puede estar vacío");
        }
    }
}
