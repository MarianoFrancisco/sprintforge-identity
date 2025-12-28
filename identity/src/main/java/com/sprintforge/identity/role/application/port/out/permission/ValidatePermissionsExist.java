package com.sprintforge.identity.role.application.port.out.permission;

import java.util.Set;
import java.util.UUID;

public interface ValidatePermissionsExist {
    void validateAllExist(Set<UUID> permissionIds);
}
