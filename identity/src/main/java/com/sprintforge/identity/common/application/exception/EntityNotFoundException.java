package com.sprintforge.identity.common.application.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {
        super("La entidad solicitada no fue encontrada.");
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}