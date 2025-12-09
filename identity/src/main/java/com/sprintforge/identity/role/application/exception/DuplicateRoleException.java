package com.sprintforge.identity.role.application.exception;

public class DuplicateRoleException extends RuntimeException {
    public DuplicateRoleException(String message) {
        super("El rol con nombre " + message + " ya existe.");
    }
}
