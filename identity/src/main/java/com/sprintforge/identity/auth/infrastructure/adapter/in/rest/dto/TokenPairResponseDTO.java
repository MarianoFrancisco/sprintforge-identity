package com.sprintforge.identity.auth.infrastructure.adapter.in.rest.dto;

import java.util.UUID;

public record TokenPairResponseDTO(
        String tokenType,
        String accessToken,
        long accessExpiresInSeconds,
        String refreshToken,
        long refreshExpiresInSeconds,
        UUID authSessionId
) {
}
