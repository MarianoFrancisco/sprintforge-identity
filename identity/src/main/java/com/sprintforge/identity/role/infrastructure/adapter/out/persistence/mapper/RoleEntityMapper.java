package com.sprintforge.identity.role.infrastructure.adapter.out.persistence.mapper;

import com.sprintforge.identity.role.domain.Role;
import com.sprintforge.identity.role.infrastructure.adapter.out.persistence.entity.RoleEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RoleEntityMapper {
    public Role toDomain(RoleEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Role(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.isDefault(),
                entity.isActive(),
                entity.isDeleted(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public RoleEntity toEntity(Role domain) {
        if (domain == null) {
            return null;
        }
        return RoleEntity.builder()
                .id(domain.getId().value())
                .name(domain.getName().value())
                .description(domain.getDescription().value())
                .isDefault(domain.isDefault())
                .isActive(domain.isActive())
                .isDeleted(domain.isDeleted())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }
}
