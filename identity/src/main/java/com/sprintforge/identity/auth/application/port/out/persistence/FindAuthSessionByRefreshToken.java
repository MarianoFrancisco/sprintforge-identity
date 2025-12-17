package com.sprintforge.identity.auth.application.port.out.persistence;

import com.sprintforge.identity.auth.domain.AuthSession;

import java.util.Optional;

public interface FindAuthSessionByRefreshToken {
    Optional<AuthSession> findByRefreshToken(String refreshToken);
}
