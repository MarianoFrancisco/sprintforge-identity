package com.sprintforge.identity.user.domain.valueobject;

import com.sprintforge.common.domain.exception.ValidationException;

public record Username(String value) {

    private static final String CUI_PATTERN = "\\d{13}";

    public Username {
        if (value == null || value.isBlank()) {
            throw new ValidationException("El nombre de usuario no puede estar vacío");
        }

        if (!value.matches(CUI_PATTERN)) {
            throw new ValidationException("El nombre de usuario debe ser un CUI válido de 13 dígitos");
        }
    }
}
