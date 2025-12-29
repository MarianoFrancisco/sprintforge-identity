package com.sprintforge.identity.auth.infrastructure.adapter.in.rest.controller;

import com.sprintforge.identity.auth.application.port.in.command.*;
import com.sprintforge.identity.auth.application.port.in.result.TokenPairResult;
import com.sprintforge.identity.auth.infrastructure.adapter.in.rest.dto.*;
import com.sprintforge.identity.auth.infrastructure.adapter.in.rest.mapper.AuthRestMapper;
import com.sprintforge.identity.auth.infrastructure.properties.AuthProperties;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final Login login;
    private final RefreshToken refreshToken;
    private final Logout logout;
    private final SetInitialPassword setInitialPassword;
    private final VerifyEmail verifyEmail;
    private final AuthProperties authProperties;

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

    @GetMapping("/verify-email")
    public void verifyEmail(
            @RequestParam("token") String token,
            HttpServletResponse response
    ) throws IOException {
        verifyEmail.handle(new VerifyEmailCommand(token));
        response.sendRedirect(authProperties.buildLink(token));
    }
}
