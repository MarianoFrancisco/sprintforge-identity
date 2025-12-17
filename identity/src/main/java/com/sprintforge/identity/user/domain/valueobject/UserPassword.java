package com.sprintforge.identity.user.domain.valueobject;

import java.util.regex.Pattern;

public record UserPassword(
        String value
) {

    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 255;

    public UserPassword {
        if (value != null && value.isBlank()) {
            if (value.length() < MIN_LENGTH || value.length() > MAX_LENGTH) {
                throw new IllegalArgumentException(
                        String.format("La contraseña debe tener entre %d y %d caracteres", MIN_LENGTH, MAX_LENGTH)
                );
            }
        }
    }
}
