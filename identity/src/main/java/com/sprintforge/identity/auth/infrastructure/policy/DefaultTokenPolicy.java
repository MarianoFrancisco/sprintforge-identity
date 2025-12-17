package com.sprintforge.identity.auth.infrastructure.policy;

import com.sprintforge.identity.auth.application.policy.TokenPolicy;
import com.sprintforge.identity.auth.infrastructure.config.security.JwtProperties;
import com.sprintforge.identity.auth.infrastructure.config.security.RefreshProperties;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.Instant;

@RequiredArgsConstructor
public class DefaultTokenPolicy implements TokenPolicy {

    private final JwtProperties jwtProperties;
    private final RefreshProperties refreshProperties;

    @Override
    public Instant accessExpiresAt(Instant now) {
        return now.plus(Duration.ofMinutes(jwtProperties.getAccessTtlMinutes()));
    }

    @Override
    public Instant refreshExpiresAt(Instant now) {
        return now.plus(Duration.ofDays(refreshProperties.getTtlDays()));
    }

    @Override
    public long accessExpiresInSeconds() {
        return Duration.ofMinutes(jwtProperties.getAccessTtlMinutes()).getSeconds();
    }

    @Override
    public long refreshExpiresInSeconds() {
        return Duration.ofDays(refreshProperties.getTtlDays()).getSeconds();
    }
}
