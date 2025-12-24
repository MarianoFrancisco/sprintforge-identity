package com.sprintforge.identity.auth.application.mapper;

import com.sprintforge.identity.auth.application.port.in.result.TokenPairResult;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class TokenPairResultMapper {
    public TokenPairResult from(
            String accessToken,
            long accessTokenExpiresAt,
            String refreshToken,
            long refreshTokenExpiresAt,
            UUID authSessionId
    ) {
        return new TokenPairResult(
                "Bearer",
                accessToken,
                accessTokenExpiresAt * 60,
                refreshToken,
                refreshTokenExpiresAt * 86400,
                authSessionId
        );
    }
}
