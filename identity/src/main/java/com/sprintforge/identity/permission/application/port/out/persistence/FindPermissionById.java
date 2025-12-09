package com.sprintforge.identity.permission.application.port.out.persistence;

import com.sprintforge.identity.permission.domain.Permission;

import java.util.Optional;
import java.util.UUID;

public interface FindPermissionById {
    Optional<Permission> findById(UUID id);
}
