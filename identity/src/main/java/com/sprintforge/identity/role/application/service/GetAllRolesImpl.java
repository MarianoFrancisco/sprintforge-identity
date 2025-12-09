package com.sprintforge.identity.role.application.service;

import com.sprintforge.identity.role.application.port.in.query.GetAllRoles;
import com.sprintforge.identity.role.application.port.in.query.GetAllRolesQuery;
import com.sprintforge.identity.role.application.port.out.persistence.FindAllRoles;
import com.sprintforge.identity.role.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetAllRolesImpl implements GetAllRoles {

    private FindAllRoles findAllRoles;

    @Override
    public List<Role> handle(GetAllRolesQuery query) {
        return findAllRoles.findAll(query);
    }

}
