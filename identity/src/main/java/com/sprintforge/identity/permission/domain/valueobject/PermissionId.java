package com.sprintforge.identity.permission.domain.valueobject;

import com.sprintforge.common.domain.exception.ValidationException;

import java.util.UUID;

public record PermissionId(UUID value) {

    public PermissionId {
        if (value == null) {
            throw new ValidationException("El identificador de permiso no puede estar vacío");
        }
    }
}