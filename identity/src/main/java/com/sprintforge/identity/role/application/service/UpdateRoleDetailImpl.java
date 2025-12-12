package com.sprintforge.identity.role.application.service;

import com.sprintforge.identity.role.application.exception.DuplicateRoleException;
import com.sprintforge.identity.role.application.exception.RoleNotFoundException;
import com.sprintforge.identity.role.application.port.in.command.UpdateRoleDetail;
import com.sprintforge.identity.role.application.port.in.command.UpdateRoleDetailCommand;
import com.sprintforge.identity.role.application.port.out.persistence.ExistsRoleByNameAndIdNot;
import com.sprintforge.identity.role.application.port.out.persistence.FindRoleById;
import com.sprintforge.identity.role.application.port.out.persistence.SaveRole;
import com.sprintforge.identity.role.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UpdateRoleDetailImpl implements UpdateRoleDetail {

    private final FindRoleById findRoleById;
    private final ExistsRoleByNameAndIdNot existsRoleByNameAndIdNot;
    private final SaveRole saveRole;

    @Override
    public Role handle(UpdateRoleDetailCommand command) {
        Role role = findRoleById.findById(command.id()).orElseThrow(
                () -> new RoleNotFoundException(command.id().toString())
        );

        if (existsRoleByNameAndIdNot.existsByNameAndIdNot(command.name(), command.id())) {
            throw new DuplicateRoleException(command.name());
        }

        role.updateDetails(command.name(), command.description());
        Role roleSaved = saveRole.save(role);
        return roleSaved;
    }
}
