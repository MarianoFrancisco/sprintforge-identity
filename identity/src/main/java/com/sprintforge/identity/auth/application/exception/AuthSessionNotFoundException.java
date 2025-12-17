package com.sprintforge.identity.auth.application.exception;

import com.sprintforge.common.application.exception.EntityNotFoundException;

import java.util.UUID;

public class AuthSessionNotFoundException extends EntityNotFoundException {

    private AuthSessionNotFoundException(String field, String value) {
        super(String.format(
                "No se encontró ninguna sesión de autenticación con %s \"%s\".",
                field,
                value
        ));
    }

    public static AuthSessionNotFoundException byId(UUID id) {
        return new AuthSessionNotFoundException("el identificador", id.toString());
    }

    public static AuthSessionNotFoundException byRefreshToken(String refreshToken) {
        return new AuthSessionNotFoundException("el token para refrescar sesión", refreshToken);
    }
}
