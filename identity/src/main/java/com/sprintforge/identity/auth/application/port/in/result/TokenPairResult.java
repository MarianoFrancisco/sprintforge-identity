package com.sprintforge.identity.auth.application.port.in.result;

import java.util.UUID;

public record TokenPairResult(
        String tokenType,
        String accessToken,
        long accessExpiresInSeconds,
        String refreshToken,
        long refreshExpiresInSeconds,
        UUID authSessionId
) {
}
