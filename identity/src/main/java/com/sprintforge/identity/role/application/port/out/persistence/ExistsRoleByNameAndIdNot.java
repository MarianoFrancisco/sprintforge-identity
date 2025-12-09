package com.sprintforge.identity.role.application.port.out.persistence;

import java.util.UUID;

public interface ExistsRoleByNameAndIdNot {
    boolean existsByNameAndIdNot(String name, UUID id);
}
