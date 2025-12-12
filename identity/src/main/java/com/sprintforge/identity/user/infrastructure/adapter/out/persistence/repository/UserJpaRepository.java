package com.sprintforge.identity.user.infrastructure.adapter.out.persistence.repository;

import com.sprintforge.identity.user.infrastructure.adapter.out.persistence.entity.UserEntity;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

@NullMarked
public interface UserJpaRepository extends
        JpaRepository<UserEntity, UUID>,
        JpaSpecificationExecutor<UserEntity> {
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<UserEntity> findById(String id);
}
