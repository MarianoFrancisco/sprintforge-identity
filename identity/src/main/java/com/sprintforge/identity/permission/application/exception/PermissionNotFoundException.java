package com.sprintforge.identity.permission.application.exception;

import com.sprintforge.identity.common.application.exception.EntityNotFoundException;

public class PermissionNotFoundException extends EntityNotFoundException {
    public PermissionNotFoundException(String type, String value) {
        super("Permiso con " + type + " " + value + " no encontrado.");
    }
}
