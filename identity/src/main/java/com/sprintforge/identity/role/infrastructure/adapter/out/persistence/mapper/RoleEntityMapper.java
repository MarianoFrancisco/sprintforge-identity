package com.sprintforge.identity.role.infrastructure.adapter.out.persistence.mapper;

import com.sprintforge.identity.permission.domain.Permission;
import com.sprintforge.identity.permission.infrastructure.adapter.out.persistence.entity.PermissionEntity;
import com.sprintforge.identity.permission.infrastructure.adapter.out.persistence.mapper.PermissionEntityMapper;
import com.sprintforge.identity.role.domain.Role;
import com.sprintforge.identity.role.infrastructure.adapter.out.persistence.entity.RoleEntity;
import lombok.experimental.UtilityClass;

import java.util.Set;
import java.util.UUID;

import static java.util.stream.Collectors.toSet;

@UtilityClass
public class RoleEntityMapper {

    public Role toDomain(RoleEntity entity) {
        if (entity == null) {
            return null;
        }

        Set<Permission> permissions = entity.getPermissions().stream()
                .map(PermissionEntityMapper::toDomain)
                .collect(toSet());

        return new Role(
                entity.getId(),
                entity.getName(),
                entity.getDescription() != null ? entity.getDescription() : null,
                entity.isDefault(),
                entity.isActive(),
                entity.isDeleted(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                permissions
        );
    }

    public RoleEntity toEntity(Role role) {
        if (role == null) {
            return null;
        }

        Set<PermissionEntity> permissions = role.getPermissions().stream()
                .map(PermissionEntityMapper::toEntity)
                .collect(toSet());

        String description = role.getDescription() != null
                ? role.getDescription().value()
                : null;

        return RoleEntity.builder()
                .id(role.getId().value())
                .name(role.getName().value())
                .description(description)
                .isDefault(role.isDefault())
                .isActive(role.isActive())
                .isDeleted(role.isDeleted())
                .createdAt(role.getCreatedAt())
                .updatedAt(role.getUpdatedAt())
                .permissions(permissions)
                .build();
    }
}
