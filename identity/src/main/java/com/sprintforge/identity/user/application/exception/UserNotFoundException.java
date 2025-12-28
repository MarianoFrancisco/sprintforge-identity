package com.sprintforge.identity.user.application.exception;

import com.sprintforge.common.application.exception.EntityNotFoundException;

import java.util.UUID;

public class UserNotFoundException extends EntityNotFoundException {

    private UserNotFoundException(String field, String value) {
        super(String.format("No se encontró ningún usuario con %s \"%s\".", field, value));
    }

    public static UserNotFoundException byId(UUID id) {
        return new UserNotFoundException("el identificador", id.toString());
    }

    public static UserNotFoundException byUsername(String username) {
        return new UserNotFoundException("el nombre de usuario", username);
    }

    public static UserNotFoundException byEmail(String email) {
        return new UserNotFoundException("el correo electrónico", email);
    }
}
