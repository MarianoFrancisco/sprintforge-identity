package com.sprintforge.identity.user.domain.valueobject;

public record Username(String value) {

    private static final String CUI_PATTERN = "\\d{13}";

    public Username {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío");
        }

        if (!value.matches(CUI_PATTERN)) {
            throw new IllegalArgumentException("El nombre de usuario debe ser un CUI válido de 13 dígitos");
        }
    }
}
