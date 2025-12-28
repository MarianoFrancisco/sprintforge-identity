package com.sprintforge.identity.user.application.exception;

import com.sprintforge.common.application.exception.BusinessException;

import java.util.UUID;

public class UserNotActiveException extends BusinessException {

    private UserNotActiveException(String detail) {
        super("El usuario " + detail + " no está activo.");
    }

    public static UserNotActiveException byId(UUID id) {
        return new UserNotActiveException(
                "con identificador \"" + id.toString() + "\""
        );
    }

    public static UserNotActiveException byUsername(String username) {
        return new UserNotActiveException(
                "con nombre de usuario \"" + username + "\""
        );
    }

    public static UserNotActiveException byEmail(String email) {
        return new UserNotActiveException(
                "con correo electrónico \"" + email + "\""
        );
    }
}
