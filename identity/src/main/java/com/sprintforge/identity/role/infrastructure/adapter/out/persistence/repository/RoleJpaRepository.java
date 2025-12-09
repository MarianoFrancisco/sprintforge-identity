package com.sprintforge.identity.role.infrastructure.adapter.out.persistence.repository;

import com.sprintforge.identity.role.infrastructure.adapter.out.persistence.entity.RoleEntity;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

@NullMarked
public interface RoleJpaRepository extends
        JpaRepository<RoleEntity, UUID>,
        JpaSpecificationExecutor<RoleEntity> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, UUID id);

    Optional<RoleEntity> findById(UUID uuid);
}
