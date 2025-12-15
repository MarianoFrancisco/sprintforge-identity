package com.sprintforge.identity.role.application.exception;

import com.sprintforge.common.application.exception.EntityNotFoundException;

public class DefaultRoleNotFoundException extends EntityNotFoundException {

    public DefaultRoleNotFoundException() {
        super("No se encontró ningún rol por defecto.");
    }
}
