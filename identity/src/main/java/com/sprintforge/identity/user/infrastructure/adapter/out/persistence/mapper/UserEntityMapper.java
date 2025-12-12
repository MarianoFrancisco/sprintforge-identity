package com.sprintforge.identity.user.infrastructure.adapter.out.persistence.mapper;

import com.sprintforge.identity.user.domain.User;
import com.sprintforge.identity.user.infrastructure.adapter.out.persistence.entity.UserEntity;
import lombok.experimental.UtilityClass;

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
                entity.getPassword(),
                entity.getEmployeeId(),
                entity.getRoleId(),
                entity.getStatus(),
                entity.getLastLoginAt(),
                entity.isDeleted(),
                entity.getEmailVerifiedAt(),
                entity.isMfaEnabled(),
                entity.getMfaSecret(),
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
                .roleId(domain.getRoleId().value())
                .status(domain.getStatus())
                .lastLoginAt(domain.getLastLoginAt())
                .isDeleted(domain.isDeleted())
                .emailVerifiedAt(domain.getEmailVerifiedAt())
                .mfaEnabled(domain.isMfaEnabled())
                .mfaSecret(domain.getMfaSecret())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }
}
