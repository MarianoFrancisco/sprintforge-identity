package com.sprintforge.identity.user.domain.valueobject;

import com.sprintforge.common.domain.exception.ValidationException;

import java.util.regex.Pattern;

public record UserPassword(
        String value
) {

    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 255;

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#._-])[A-Za-z\\d@$!%*?&#._-]+$"
    );

    public UserPassword {
        if (value != null) {
            if (value.length() < MIN_LENGTH || value.length() > MAX_LENGTH) {
                throw new ValidationException(
                        "La contraseña debe tener entre " + MIN_LENGTH + " y " + MAX_LENGTH + " caracteres"
                );
            }

            if (!PASSWORD_PATTERN.matcher(value).matches()) {
                throw new ValidationException(
                        "La contraseña debe contener mayúsculas, minúsculas, números y un carácter especial"
                );
            }
        }
    }
}
