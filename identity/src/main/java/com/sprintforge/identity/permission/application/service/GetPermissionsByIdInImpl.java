package com.sprintforge.identity.permission.application.service;

import com.sprintforge.identity.permission.application.exception.InvalidRolePermissionException;
import com.sprintforge.identity.permission.application.port.in.query.GetPermissionsByIdIn;
import com.sprintforge.identity.permission.application.port.in.query.GetPermissionsByIdInQuery;
import com.sprintforge.identity.permission.application.port.out.persistence.FindPermissionsByIdIn;
import com.sprintforge.identity.permission.domain.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetPermissionsByIdInImpl implements GetPermissionsByIdIn {

    private final FindPermissionsByIdIn findPermissionsByIdIn;

    @Override
    public Set<Permission> handle(GetPermissionsByIdInQuery query) {
        Set<Permission> permissions = findPermissionsByIdIn.findByIdIn(query.ids());
        if (query.ids().size() != permissions.size()) {
            throw new InvalidRolePermissionException(query.ids());
        }
        return permissions;
    }
}
