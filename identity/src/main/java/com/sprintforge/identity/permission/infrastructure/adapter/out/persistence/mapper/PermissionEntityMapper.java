package com.sprintforge.identity.permission.infrastructure.adapter.out.persistence.mapper;

import com.sprintforge.identity.permission.domain.Permission;
import com.sprintforge.identity.permission.infrastructure.adapter.out.persistence.entity.PermissionEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PermissionEntityMapper {
    public Permission toDomain(PermissionEntity entity) {
        return new Permission(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getDescription(),
                entity.getCategory()
        );
    }
}
