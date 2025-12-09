package com.sprintforge.identity.permission.application.service;

import com.sprintforge.identity.permission.application.exception.PermissionNotFoundException;
import com.sprintforge.identity.permission.application.port.in.query.GetPermissionById;
import com.sprintforge.identity.permission.application.port.in.query.GetPermissionByIdQuery;
import com.sprintforge.identity.permission.application.port.out.persistence.FindPermissionById;
import com.sprintforge.identity.permission.domain.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetPermissionByIdImpl implements GetPermissionById {

    private final FindPermissionById findPermissionById;

    @Override
    public Permission handle(GetPermissionByIdQuery query) {
        return findPermissionById.findById(query.id()).orElseThrow(
                () -> new PermissionNotFoundException("identificador", query.id().toString())
        );
    }
}
