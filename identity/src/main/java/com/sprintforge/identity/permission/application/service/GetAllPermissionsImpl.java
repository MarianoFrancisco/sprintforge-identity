package com.sprintforge.identity.permission.application.service;

import com.sprintforge.identity.permission.application.port.in.query.GetAllPermissions;
import com.sprintforge.identity.permission.application.port.in.query.GetAllPermissionsQuery;
import com.sprintforge.identity.permission.application.port.out.persistence.FindAllPermissions;
import com.sprintforge.identity.permission.domain.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetAllPermissionsImpl implements GetAllPermissions {

    private final FindAllPermissions findAllPermissions;

    @Override
    public List<Permission> handle(GetAllPermissionsQuery query) {
        return findAllPermissions.findAll(query);
    }
}
