package com.sprintforge.identity.user.application.exception;

import com.sprintforge.identity.common.application.exception.DuplicateEntityException;

import java.util.UUID;

public class DuplicateUserException extends DuplicateEntityException {

    private DuplicateUserException(String field, String value) {
        super(String.format("El usuario con %s \"%s\" ya existe.", field, value));
    }

    public static DuplicateUserException byUsername(String username) {
        return new DuplicateUserException("el nombre de usuario", username);
    }

    public static DuplicateUserException byEmail(String email) {
        return new DuplicateUserException("el correo electrónico", email);
    }

    public static DuplicateUserException byEmployeeId(UUID employeeId) {
        return new DuplicateUserException("el identificador de empleado", employeeId.toString());
    }
}
