package com.sprintforge.identity.role.application.port.out.persistence;

import com.sprintforge.identity.role.domain.Role;

import java.util.Optional;
import java.util.UUID;

public interface FindRoleById {
    Optional<Role> findById(UUID id);
}
