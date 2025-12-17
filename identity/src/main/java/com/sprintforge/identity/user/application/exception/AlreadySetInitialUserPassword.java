package com.sprintforge.identity.user.application.exception;

public class AlreadySetInitialUserPassword extends RuntimeException {
    public AlreadySetInitialUserPassword() {
        super("Ya se ha establecido la contraseña inicial para este usuario.");
    }
}
