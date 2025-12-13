package com.sprintforge.identity.role.domain.valueobject;

import com.sprintforge.common.domain.exception.ValidationException;

public record RoleDescription(
        String value
) {
    public RoleDescription {
        if (value != null && value.length() > 250) {
            throw new ValidationException("La descripción del rol no puede exceder los 250 caracteres.");
        }
    }
}
