package com.sprintforge.identity.role.application.service;

import com.sprintforge.identity.role.application.exception.RoleNotFoundException;
import com.sprintforge.identity.role.application.port.in.query.GetRoleById;
import com.sprintforge.identity.role.application.port.in.query.GetRoleByIdQuery;
import com.sprintforge.identity.role.application.port.out.persistence.FindRoleById;
import com.sprintforge.identity.role.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetRoleByIdImpl implements GetRoleById {

    private final FindRoleById findRoleById;

    @Override
    public Role handle(GetRoleByIdQuery query) {
        return findRoleById.findById(query.id()).orElseThrow(
                () -> RoleNotFoundException.byId(query.id())
        );
    }
}
