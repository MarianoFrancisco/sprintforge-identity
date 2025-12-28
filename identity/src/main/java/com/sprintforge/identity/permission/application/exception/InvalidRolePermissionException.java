package com.sprintforge.identity.permission.application.exception;

import com.sprintforge.common.application.exception.BusinessException;

import java.util.Set;
import java.util.UUID;

public class InvalidRolePermissionException extends BusinessException {
    public InvalidRolePermissionException(Set<UUID> invalidIds) {
        super("El rol contiene permisos inexistentes: " + invalidIds);
    }
}
