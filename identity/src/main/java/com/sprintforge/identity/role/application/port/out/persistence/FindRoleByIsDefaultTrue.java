package com.sprintforge.identity.role.application.port.out.persistence;

import com.sprintforge.identity.role.domain.Role;

import java.util.Optional;

public interface FindRoleByIsDefaultTrue {
    Optional<Role> findDefaultRole();
}
