package com.sprintforge.identity.auth.domain;

import com.sprintforge.identity.auth.domain.valueobject.*;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class AuthSession {

    private final AuthSessionId id;
    private final AuthSessionUserId userId;
    private AuthSessionRefreshToken refreshToken;
    private final AuthSessionUserAgent userAgent;
    private final AuthSessionIpAddress ipAddress;
    private final Instant createdAt;
    private Instant expiresAt;
    private Instant revokedAt;
    private AuthSessionRevokedReason revokedReason;

    public AuthSession(
            UUID userId,
            String refreshToken,
            String userAgent,
            String ipAddress,
            Instant createdAt,
            Instant expiresAt
    ) {
        this.id = AuthSessionId.generate();
        this.userId = AuthSessionUserId.of(userId);
        this.refreshToken = AuthSessionRefreshToken.of(refreshToken);
        this.userAgent = AuthSessionUserAgent.of(userAgent);
        this.ipAddress = AuthSessionIpAddress.of(ipAddress);
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }

    public AuthSession(
            UUID id,
            UUID userId,
            String refreshToken,
            String userAgent,
            String ipAddress,
            Instant createdAt,
            Instant expiresAt,
            Instant revokedAt,
            String revokedReason
    ) {
        this.id = AuthSessionId.of(id);
        this.userId = AuthSessionUserId.of(userId);
        this.refreshToken = AuthSessionRefreshToken.of(refreshToken);
        this.userAgent = AuthSessionUserAgent.of(userAgent);
        this.ipAddress = AuthSessionIpAddress.of(ipAddress);
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.revokedAt = revokedAt;
        this.revokedReason = AuthSessionRevokedReason.of(revokedReason);
    }

    public void revoke(Instant revokedAt, String revokedReason) {
        this.revokedAt = revokedAt;
        this.revokedReason = AuthSessionRevokedReason.of(revokedReason);
    }

    public boolean compareRefreshToken(String refreshToken) {
        return this.refreshToken.value().equals(refreshToken);
    }

    public boolean validateRefreshTokenExpiration(Instant now) {
        return this.expiresAt.isBefore(now);
    }

    public void rotateRefreshToken(
            String newRefreshToken,
            Instant newExpiresAt,
            Instant now
    ) {
        this.refreshToken = AuthSessionRefreshToken.of(newRefreshToken);
        this.expiresAt = newExpiresAt;
    }

}
