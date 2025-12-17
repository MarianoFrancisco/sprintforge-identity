package com.sprintforge.identity.auth.application.mapper;

import com.sprintforge.identity.auth.application.port.in.command.LoginCommand;
import com.sprintforge.identity.auth.domain.AuthSession;
import com.sprintforge.identity.user.application.port.in.result.UserAuthDataResult;
import lombok.experimental.UtilityClass;

import java.time.Instant;

@UtilityClass
public class AuthSessionMapper {
    public AuthSession toDomain(
            UserAuthDataResult result,
            String refreshToken,
            LoginCommand command,
            Instant createdAt,
            Instant expiresAt
    ) {
        return new AuthSession(
                result.userId(),
                refreshToken,
                command.userAgent(),
                command.ipAddress(),
                createdAt,
                expiresAt
        );
    }
}
