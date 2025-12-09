package com.sprintforge.identity.permission.domain.valueobject;

public record PermissionCode(String value) {

    private static final String PATTERN = "^[A-Z_]+:[A-Z_]+$";

    public PermissionCode {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El código del permiso no puede estar vacío.");
        }

        if (value.length() > 100) {
            throw new IllegalArgumentException("El código del permiso no puede exceder los 100 caracteres.");
        }

        if (!value.matches(PATTERN)) {
            throw new IllegalArgumentException(
                    "Formato incorrecto. Use mayúsculas y guion bajo. Ejemplo: GESTION_USUARIOS:CREAR."
            );
        }
    }
}
