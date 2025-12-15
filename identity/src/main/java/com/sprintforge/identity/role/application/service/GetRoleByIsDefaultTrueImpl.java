package com.sprintforge.identity.role.application.service;

import com.sprintforge.identity.role.application.exception.DefaultRoleNotFoundException;
import com.sprintforge.identity.role.application.port.in.query.GetRoleByIsDefaultTrue;
import com.sprintforge.identity.role.application.port.out.persistence.FindRoleByIsDefaultTrue;
import com.sprintforge.identity.role.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetRoleByIsDefaultTrueImpl implements GetRoleByIsDefaultTrue {

    private final FindRoleByIsDefaultTrue findDefaultRole;

    @Override
    public Role handle() {
        return findDefaultRole.findDefaultRole().orElseThrow(
                DefaultRoleNotFoundException::new
        );
    }
}
