package com.sprintforge.identity.auth.infrastructure.adapter.in.rest.mapper;

import com.sprintforge.identity.auth.application.port.in.command.LoginCommand;
import com.sprintforge.identity.auth.application.port.in.command.LogoutCommand;
import com.sprintforge.identity.auth.application.port.in.command.RefreshTokenCommand;
import com.sprintforge.identity.auth.application.port.in.command.SetInitialPasswordCommand;
import com.sprintforge.identity.auth.application.port.in.result.TokenPairResult;
import com.sprintforge.identity.auth.infrastructure.adapter.in.rest.dto.*;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class AuthRestMapper {
    public LoginCommand toLoginCommand(
            LoginRequestDTO dto,
            String userAgent,
            String ipAddress
    ) {
        return new LoginCommand(
                dto.email(),
                dto.password(),
                userAgent,
                ipAddress
        );
    }

    public RefreshTokenCommand toRefreshTokenCommand(
            RefreshTokenRequestDTO dto,
            String userAgent,
            String ipAddress
    ) {
        return new RefreshTokenCommand(
                dto.authSessionId(),
                dto.refreshToken(),
                userAgent,
                ipAddress
        );
    }

    public TokenPairResponseDTO toTokenPairResponse(TokenPairResult tokenPair) {
        return new TokenPairResponseDTO(
                tokenPair.tokenType(),
                tokenPair.accessToken(),
                tokenPair.accessExpiresInSeconds(),
                tokenPair.refreshToken(),
                tokenPair.refreshExpiresInSeconds(),
                tokenPair.authSessionId()
        );
    }

    public LogoutCommand toLogoutCommand(LogoutRequestDTO dto) {
        return new LogoutCommand(dto.authSessionId());
    }

    public SetInitialPasswordCommand toSetInitialPasswordCommand(
            SetInitialPasswordRequestDTO dto
    ) {
        return new SetInitialPasswordCommand(
                dto.token(),
                dto.userId(),
                dto.newPassword()
        );
    }
}
