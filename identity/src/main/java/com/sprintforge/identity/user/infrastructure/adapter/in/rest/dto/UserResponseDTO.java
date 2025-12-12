package com.sprintforge.identity.user.infrastructure.adapter.in.rest.dto;

import com.sprintforge.identity.user.domain.valueobject.UserStatus;

import java.time.Instant;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String username,
        String email,
        String passwordHash,
        UUID employeeId,
        UUID roleId,
        UserStatus status,
        Instant lastLoginAt,
        boolean isDeleted,
        Instant emailVerifiedAt,
        boolean mfaEnabled,
        String mfaSecret,
        Instant createdAt,
        Instant updatedAt
) {
}
