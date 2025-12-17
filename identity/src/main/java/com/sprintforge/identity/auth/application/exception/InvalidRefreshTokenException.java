package com.sprintforge.identity.auth.application.exception;

import com.sprintforge.common.application.exception.BusinessException;

public class InvalidRefreshTokenException extends BusinessException {
    public InvalidRefreshTokenException() {
        super("El token de actualización proporcionado no es válido.");
    }
}
