package com.sprintforge.identity.role.application.service;

import com.sprintforge.identity.role.application.exception.RoleNotFoundException;
import com.sprintforge.identity.role.application.port.in.command.ActivateRole;
import com.sprintforge.identity.role.application.port.in.command.ActivateRoleCommand;
import com.sprintforge.identity.role.application.port.out.persistence.FindRoleById;
import com.sprintforge.identity.role.application.port.out.persistence.SaveRole;
import com.sprintforge.identity.role.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ActivateRoleImpl implements ActivateRole {

    private final FindRoleById findRoleById;
    private final SaveRole saveRole;

    @Override
    public Role handle(ActivateRoleCommand command) {
        Role role = findRoleById.findById(command.id()).orElseThrow(
                () -> RoleNotFoundException.byId(command.id())
        );
        role.activate();
        Role roleSaved = saveRole.save(role);
        return roleSaved;
    }
}
