package com.sprintforge.identity.auth.infrastructure.adapter.out.persistence.mapper;

import com.sprintforge.identity.auth.domain.AuthSession;
import com.sprintforge.identity.auth.infrastructure.adapter.out.persistence.entity.AuthSessionEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthSessionEntityMapper {

    public AuthSession toDomain(AuthSessionEntity entity) {
        if (entity == null) {
            return null;
        }

        return new AuthSession(
                entity.getId(),
                entity.getUserId(),
                entity.getRefreshToken(),
                entity.getUserAgent() != null ? entity.getUserAgent() : null,
                entity.getIpAddress() != null ? entity.getIpAddress() : null,
                entity.getCreatedAt(),
                entity.getExpiresAt(),
                entity.getRevokedAt() != null ? entity.getRevokedAt() : null,
                entity.getRevokedReason() != null ? entity.getRevokedReason() : null
        );
    }

    public AuthSessionEntity toEntity(AuthSession domain) {
        if (domain == null) {
            return null;
        }

        return AuthSessionEntity.builder()
                .id(domain.getId().value())
                .userId(domain.getUserId().value())
                .refreshToken(domain.getRefreshToken().value())
                .userAgent(domain.getUserAgent().value() != null ? domain.getUserAgent().value() : null)
                .ipAddress(domain.getIpAddress().value() != null ? domain.getIpAddress().value() : null)
                .createdAt(domain.getCreatedAt())
                .expiresAt(domain.getExpiresAt())
                .revokedAt(domain.getRevokedAt() != null ? domain.getRevokedAt() : null)
                .revokedReason(domain.getRevokedReason() != null ? domain.getRevokedReason().value() : null)
                .build();
    }
}
