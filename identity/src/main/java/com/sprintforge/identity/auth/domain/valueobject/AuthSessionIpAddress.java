package com.sprintforge.identity.auth.domain.valueobject;

import com.sprintforge.common.domain.exception.ValidationException;

import java.net.InetAddress;
import java.net.UnknownHostException;

public record AuthSessionIpAddress(
        String value
) {
    public AuthSessionIpAddress {
        if (value != null) {
            if (value.length() > 50) {
                throw new ValidationException("La dirección IP no puede exceder los 50 caracteres");
            }
            if (!isValidIp(value)) {
                throw new ValidationException("La dirección IP '" + value + "' no es válida");
            }
        }
    }

    public static AuthSessionIpAddress of(String value) {
        return new AuthSessionIpAddress(value);
    }

    private static boolean isValidIp(String ip) {
        try {
            InetAddress address = InetAddress.getByName(ip);
            return address != null;
        } catch (UnknownHostException e) {
            return false;
        }
    }
}
