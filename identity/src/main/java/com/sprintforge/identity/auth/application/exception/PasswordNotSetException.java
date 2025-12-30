package com.sprintforge.identity.auth.application.exception;

public class PasswordNotSetException extends RuntimeException {

    private final String token;

    public PasswordNotSetException(String token) {
        super("El usuario debe establecer una contraseña para poder autenticarse.");
        this.token = token;
    }

    public String token() {
        return token;
    }
}
