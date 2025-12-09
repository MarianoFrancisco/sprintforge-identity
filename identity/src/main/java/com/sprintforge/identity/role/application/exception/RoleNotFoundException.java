package com.sprintforge.identity.role.application.exception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String message) {
        super("Rol con identificador " + message + " no encontrado.");
    }
}
