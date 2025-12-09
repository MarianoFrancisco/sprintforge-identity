package com.sprintforge.identity.role.domain.valueobject;

import java.util.UUID;

public record RoleId(
        UUID value
) {
    public RoleId {
        if (value == null) {
            throw new IllegalArgumentException("El identificador de rol no puede estar vacío.");
        }
    }
}