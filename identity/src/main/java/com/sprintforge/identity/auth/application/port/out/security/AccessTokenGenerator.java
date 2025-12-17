package com.sprintforge.identity.auth.application.port.out.security;

import java.time.Instant;
import java.util.UUID;

public interface AccessTokenGenerator {
    String generate(
            UUID userId,
            UUID roleId,
            Instant issuedAt,
            Instant expiresAt,
            UUID employeeId,
            UUID authSessionId
    );
}
