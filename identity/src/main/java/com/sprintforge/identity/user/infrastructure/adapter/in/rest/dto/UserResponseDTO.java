package com.sprintforge.identity.user.infrastructure.adapter.in.rest.dto;

import com.sprintforge.identity.role.infrastructure.adapter.in.rest.dto.RoleResponseDTO;
import com.sprintforge.identity.user.domain.valueobject.UserStatus;

import java.time.Instant;
import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String username,
        String email,
        String passwordHash,
        UUID employeeId,
        RoleResponseDTO role,
        UserStatus status,
        Instant lastLoginAt,
        Instant emailVerifiedAt,
        boolean mfaEnabled,
        String mfaSecret,
        Instant createdAt,
        Instant updatedAt
) {
}
