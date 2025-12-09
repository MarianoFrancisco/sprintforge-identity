package com.sprintforge.identity.role.application.service;

import com.sprintforge.identity.role.application.exception.DuplicateRoleException;
import com.sprintforge.identity.role.application.mapper.RoleMapper;
import com.sprintforge.identity.role.application.port.in.command.CreateRole;
import com.sprintforge.identity.role.application.port.in.command.CreateRoleCommand;
import com.sprintforge.identity.role.application.port.out.persistence.ExistRoleByName;
import com.sprintforge.identity.role.application.port.out.persistence.SaveRole;
import com.sprintforge.identity.role.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CreateRoleImpl implements CreateRole {

    private final ExistRoleByName existRoleByName;
    private final SaveRole saveRole;

    @Override
    public Role handle(CreateRoleCommand command) {
        if (existRoleByName.existsByName(command.name())) {
            throw new DuplicateRoleException(command.name());
        }
        Role role = RoleMapper.toDomain(command);
        Role roleSaved = saveRole.save(role);
        return roleSaved;
    }
}
