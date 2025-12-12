package com.sprintforge.identity.role.application.exception;

import com.sprintforge.identity.common.application.exception.DuplicateEntityException;

public class DuplicateRoleException extends DuplicateEntityException {
    public DuplicateRoleException(String message) {
        super("El rol con nombre " + message + " ya existe.");
    }
}
