package com.sprintforge.identity.role.application.exception;

import com.sprintforge.common.application.exception.EntityNotFoundException;

import java.util.UUID;

public class RoleNotFoundException extends EntityNotFoundException {

    private RoleNotFoundException(String field, String value) {
        super(String.format("No se encontró ningún rol con %s \"%s\".", field, value));
    }

    public static RoleNotFoundException byId(UUID id) {
        return new RoleNotFoundException("el identificador", id.toString());
    }

    public static RoleNotFoundException byName(String name) {
        return new RoleNotFoundException("el nombre", name);
    }
}
