package com.sprintforge.identity.permission.application.port.out.persistence;

import com.sprintforge.identity.permission.domain.Permission;

import java.util.Optional;

public interface FindPermissionByCode {
    Optional<Permission> findByCode(String code);
}
