package com.sprintforge.identity.role.infrastructure.adapter.out.persistence.mapper;

import com.sprintforge.identity.permission.infrastructure.adapter.out.persistence.entity.PermissionEntity;
import com.sprintforge.identity.role.domain.Role;
import com.sprintforge.identity.role.infrastructure.adapter.out.persistence.entity.RoleEntity;
import lombok.experimental.UtilityClass;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@UtilityClass
public class RoleEntityMapper {

    public Role toDomain(RoleEntity entity) {
        Set<UUID> permissionIds = entity.getPermissions().stream()
                .map(PermissionEntity::getId)
                .collect(Collectors.toSet());

        return new Role(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.isDefault(),
                entity.isActive(),
                entity.isDeleted(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                permissionIds
        );
    }

    public RoleEntity toEntity(Role role) {
        RoleEntity entity = new RoleEntity();

        entity.setId(role.getId().value());
        entity.setName(role.getName().value());
        entity.setDescription(role.getDescription().value());
        entity.setDefault(role.isDefault());
        entity.setActive(role.isActive());
        entity.setDeleted(role.isDeleted());
        entity.setCreatedAt(role.getCreatedAt());
        entity.setUpdatedAt(role.getUpdatedAt());

        Set<PermissionEntity> permissions = role.getPermissionIds().stream()
                .map(RoleEntityMapper::toPermissionEntityWithId)
                .collect(Collectors.toSet());

        entity.setPermissions(permissions);

        return entity;
    }

    private PermissionEntity toPermissionEntityWithId(UUID id) {
        PermissionEntity entity = new PermissionEntity();
        entity.setId(id);
        return entity;
    }
}
