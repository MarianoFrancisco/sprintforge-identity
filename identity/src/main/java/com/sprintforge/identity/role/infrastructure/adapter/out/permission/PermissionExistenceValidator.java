package com.sprintforge.identity.role.infrastructure.adapter.out.permission;

import com.sprintforge.identity.permission.application.port.out.persistence.CountPermissionsById;
import com.sprintforge.identity.role.application.exception.InvalidRolePermissionException;
import com.sprintforge.identity.role.application.port.out.permission.ValidatePermissionsExist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PermissionExistenceValidator implements ValidatePermissionsExist {

    private final CountPermissionsById countPermissionsById;

    @Override
    public void validateAllExist(Set<UUID> permissionIds) {
        if (permissionIds == null || permissionIds.isEmpty()) {
            return;
        }

        long count = countPermissionsById.countByIdIn(permissionIds);

        if (count != permissionIds.size()) {
            throw new InvalidRolePermissionException(permissionIds);
        }
    }
}
