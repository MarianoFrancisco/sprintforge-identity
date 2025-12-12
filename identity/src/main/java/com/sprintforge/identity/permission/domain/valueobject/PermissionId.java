package com.sprintforge.identity.permission.domain.valueobject;

import java.util.UUID;

public record PermissionId(UUID value) {

    public PermissionId {
        if (value == null) {
            throw new IllegalArgumentException("El identificador de permiso no puede estar vacío");
        }
    }
}