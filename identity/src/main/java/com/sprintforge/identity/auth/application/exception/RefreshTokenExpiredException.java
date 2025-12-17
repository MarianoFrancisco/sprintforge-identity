package com.sprintforge.identity.auth.application.exception;

import com.sprintforge.common.application.exception.BusinessException;

public class RefreshTokenExpiredException extends BusinessException {
    public RefreshTokenExpiredException() {
        super("El token de actualización ha expirado.");
    }
}
