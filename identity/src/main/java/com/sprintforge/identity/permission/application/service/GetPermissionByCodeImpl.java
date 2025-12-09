package com.sprintforge.identity.permission.application.service;

import com.sprintforge.identity.permission.application.exception.PermissionNotFoundException;
import com.sprintforge.identity.permission.application.port.in.query.GetPermissionByCode;
import com.sprintforge.identity.permission.application.port.in.query.GetPermissionByCodeQuery;
import com.sprintforge.identity.permission.application.port.out.persistence.FindPermissionByCode;
import com.sprintforge.identity.permission.domain.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetPermissionByCodeImpl implements GetPermissionByCode {

    private final FindPermissionByCode findPermissionByCode;

    @Override
    public Permission handle(GetPermissionByCodeQuery query) {
        return findPermissionByCode.findByCode(query.code()).orElseThrow(
                () -> new PermissionNotFoundException("código", query.code())
        );
    }
}
