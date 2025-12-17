package com.sprintforge.identity.auth.application.exception;

import com.sprintforge.common.application.exception.BusinessException;

public class InvalidCredentialsException extends BusinessException {
    public InvalidCredentialsException() {
        super("El correo o la contraseña proporcionados no son válidos.");
    }
}
