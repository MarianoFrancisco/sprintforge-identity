package com.sprintforge.identity.role.application.exception;

import com.sprintforge.common.application.exception.EntityNotFoundException;

public class RoleNotFoundException extends EntityNotFoundException {
    public RoleNotFoundException(String message) {
        super("Rol con identificador " + message + " no encontrado.");
    }
}
