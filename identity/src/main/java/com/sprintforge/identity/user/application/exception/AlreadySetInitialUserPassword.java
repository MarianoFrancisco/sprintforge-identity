package com.sprintforge.identity.user.application.exception;

import com.sprintforge.common.application.exception.BusinessException;

public class AlreadySetInitialUserPassword extends BusinessException {
    public AlreadySetInitialUserPassword() {
        super("Ya se ha establecido la contraseña inicial para este usuario.");
    }
}
