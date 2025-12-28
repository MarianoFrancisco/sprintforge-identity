package com.sprintforge.identity.auth.infrastructure.adapter.in.rest.controller;

import com.sprintforge.identity.auth.application.port.in.command.Login;
import com.sprintforge.identity.auth.application.port.in.command.Logout;
import com.sprintforge.identity.auth.application.port.in.command.RefreshToken;
import com.sprintforge.identity.auth.application.port.in.command.SetInitialPassword;
import com.sprintforge.identity.auth.application.port.in.result.TokenPairResult;
import com.sprintforge.identity.auth.infrastructure.adapter.in.rest.dto.*;
import com.sprintforge.identity.auth.infrastructure.adapter.in.rest.mapper.AuthRestMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final Login login;
    private final RefreshToken refreshToken;
    private final Logout logout;
    private final SetInitialPassword setInitialPassword;

    @PostMapping("/login")
    public TokenPairResponseDTO login(
            @RequestBody @Valid LoginRequestDTO dto,
            HttpServletRequest request
    ) {
        String userAgent = request.getHeader("User-Agent");
        String ipAddress = request.getRemoteAddr();
        TokenPairResult tokenPair = login.handle(
                AuthRestMapper.toLoginCommand(
                        dto,
                        userAgent,
                        ipAddress
                )
        );
        return AuthRestMapper.toTokenPairResponse(tokenPair);
    }

    @PostMapping("/refresh")
    public TokenPairResponseDTO refresh(
            @RequestBody @Valid RefreshTokenRequestDTO dto,
            HttpServletRequest request
    ) {
        String userAgent = request.getHeader("User-Agent");
        String ipAddress = request.getRemoteAddr();
        TokenPairResult tokenPair = refreshToken.handle(
                AuthRestMapper.toRefreshTokenCommand(
                        dto,
                        userAgent,
                        ipAddress
                )
        );
        return AuthRestMapper.toTokenPairResponse(tokenPair);
    }

    @PostMapping("/logout")
    public void logout(
            @RequestBody @Valid LogoutRequestDTO dto
    ) {
        logout.handle(
                AuthRestMapper.toLogoutCommand(dto)
        );
    }

    @PostMapping("/set-initial-password")

    public void setInitialPassword(
            @RequestBody @Valid SetInitialPasswordRequestDTO dto
    ) {
        setInitialPassword.handle(
                AuthRestMapper.toSetInitialPasswordCommand(dto)
        );
    }
}
