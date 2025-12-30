package com.sprintforge.identity.auth.application.exception;

import com.sprintforge.common.application.exception.BusinessException;

public class EmailNotVerifiedException extends BusinessException {
    public EmailNotVerifiedException() {
        super("El correo electrónico no ha sido verificado.");
    }
}
