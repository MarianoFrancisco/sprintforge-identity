package com.sprintforge.identity.permission.infrastructure.adapter.out.persistence.repository;

import com.sprintforge.identity.permission.infrastructure.adapter.out.persistence.entity.PermissionEntity;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

@NullMarked
public interface PermissionJpaRepository extends
        JpaRepository<PermissionEntity, UUID>,
        JpaSpecificationExecutor<PermissionEntity> {
    Optional<PermissionEntity> findByCode(String code);

    Optional<PermissionEntity> findById(UUID id);
}
