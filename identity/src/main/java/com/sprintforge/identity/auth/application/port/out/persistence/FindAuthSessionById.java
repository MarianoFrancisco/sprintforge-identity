package com.sprintforge.identity.auth.application.port.out.persistence;

import com.sprintforge.identity.auth.domain.AuthSession;

import java.util.Optional;
import java.util.UUID;

public interface FindAuthSessionById {
    Optional<AuthSession> findById(UUID id);
}
