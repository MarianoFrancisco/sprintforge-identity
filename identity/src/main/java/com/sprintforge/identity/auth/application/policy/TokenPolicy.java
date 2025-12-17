package com.sprintforge.identity.auth.application.policy;

import java.time.Instant;

public interface TokenPolicy {

    Instant accessExpiresAt(Instant now);

    Instant refreshExpiresAt(Instant now);

    long accessExpiresInSeconds();

    long refreshExpiresInSeconds();
}
