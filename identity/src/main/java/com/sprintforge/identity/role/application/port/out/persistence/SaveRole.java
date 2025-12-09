package com.sprintforge.identity.role.application.port.out.persistence;

import com.sprintforge.identity.role.domain.Role;

public interface SaveRole {
    Role save(Role role);
}
