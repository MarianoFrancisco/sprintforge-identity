package com.sprintforge.identity.role.application.service;

import com.sprintforge.identity.role.application.exception.RoleNotFoundException;
import com.sprintforge.identity.role.application.port.in.command.DeleteRole;
import com.sprintforge.identity.role.application.port.in.command.DeleteRoleCommand;
import com.sprintforge.identity.role.application.port.out.persistence.FindRoleById;
import com.sprintforge.identity.role.application.port.out.persistence.SaveRole;
import com.sprintforge.identity.role.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class DeleteRoleImpl implements DeleteRole {

    private final FindRoleById findRoleById;
    private final SaveRole saveRole;

    @Override
    public void handle(DeleteRoleCommand command) {
        Role role = findRoleById.findById(command.id()).orElseThrow(
                () -> new RoleNotFoundException(command.id().toString())
        );
        role.delete();
        saveRole.save(role);
    }
}
