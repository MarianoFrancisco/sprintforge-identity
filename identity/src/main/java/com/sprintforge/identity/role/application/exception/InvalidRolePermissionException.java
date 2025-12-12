package com.sprintforge.identity.role.application.exception;

import java.util.Set;
import java.util.UUID;

public class InvalidRolePermissionException extends RuntimeException {
    public InvalidRolePermissionException(Set<UUID> invalidIds) {
        super("El rol contiene permisos inexistentes: " + invalidIds);
    }
}
