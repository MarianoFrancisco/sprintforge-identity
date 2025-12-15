package com.sprintforge.identity.permission.application.port.out.persistence;

import com.sprintforge.identity.permission.domain.Permission;

import java.util.Set;
import java.util.UUID;

public interface FindPermissionsByIdIn {
    Set<Permission> findByIdIn(Set<UUID> ids);
}
