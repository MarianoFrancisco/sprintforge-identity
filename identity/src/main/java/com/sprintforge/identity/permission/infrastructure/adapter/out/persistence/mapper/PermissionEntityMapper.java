package com.sprintforge.identity.permission.infrastructure.adapter.out.persistence.mapper;

import com.sprintforge.identity.permission.domain.Permission;
import com.sprintforge.identity.permission.infrastructure.adapter.out.persistence.entity.PermissionEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PermissionEntityMapper {
    public Permission toDomain(PermissionEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Permission(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getDescription() != null ? entity.getDescription() : null,
                entity.getCategory()
        );
    }

    public PermissionEntity toEntity(Permission domain) {
        if (domain == null) {
            return null;
        }

        String description = domain.getDescription() != null
                ? domain.getDescription().value()
                : null;

        return PermissionEntity.builder()
                .id(domain.getId().value())
                .code(domain.getCode().value())
                .name(domain.getName().value())
                .description(description)
                .category(domain.getCategory().value())
                .build();
    }
}
