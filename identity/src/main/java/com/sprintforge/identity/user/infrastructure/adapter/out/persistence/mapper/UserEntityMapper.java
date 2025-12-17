package com.sprintforge.identity.user.infrastructure.adapter.out.persistence.mapper;

import com.sprintforge.identity.role.infrastructure.adapter.out.persistence.mapper.RoleEntityMapper;
import com.sprintforge.identity.user.domain.User;
import com.sprintforge.identity.user.infrastructure.adapter.out.persistence.entity.UserEntity;
import lombok.experimental.UtilityClass;

import java.time.Instant;

@UtilityClass
public class UserEntityMapper {

    public User toDomain(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        return new User(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPassword() != null ? entity.getPassword() : null,
                entity.getEmployeeId(),
                RoleEntityMapper.toDomain(entity.getRole()),
                entity.getStatus(),
                entity.getLastLoginAt() != null ? entity.getLastLoginAt() : null,
                entity.getEmailVerifiedAt() != null ? entity.getEmailVerifiedAt() : null,
                entity.isMfaEnabled(),
                entity.getMfaSecret() != null ? entity.getMfaSecret() : null,
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public UserEntity toEntity(User domain) {
        if (domain == null) {
            return null;
        }

        String password = domain.getPassword() != null
                ? domain.getPassword().value()
                : null;

        return UserEntity.builder()
                .id(domain.getId().value())
                .username(domain.getUsername().value())
                .email(domain.getEmail().value())
                .password(password)
                .employeeId(domain.getEmployeeId().value())
                .role(RoleEntityMapper.toEntity(domain.getRole()))
                .status(domain.getStatus())
                .lastLoginAt(domain.getLastLoginAt() != null ? domain.getLastLoginAt() : null)
                .emailVerifiedAt(domain.getEmailVerifiedAt() != null ? domain.getEmailVerifiedAt() : null)
                .mfaEnabled(domain.isMfaEnabled())
                .mfaSecret(domain.getMfaSecret() != null ? domain.getMfaSecret() : null)
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }
}
