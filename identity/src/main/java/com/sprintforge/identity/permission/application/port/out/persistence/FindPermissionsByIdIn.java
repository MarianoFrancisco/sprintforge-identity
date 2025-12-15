package com.sprintforge.identity.permission.application.port.out.persistence;

import java.util.Set;
import java.util.UUID;

public interface FindPermissionsByIds {
    long countByIdIn(Set<UUID> ids);
}
