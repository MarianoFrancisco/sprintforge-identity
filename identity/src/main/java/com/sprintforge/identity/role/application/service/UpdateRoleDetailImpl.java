package com.sprintforge.identity.role.application.service;

import com.sprintforge.identity.permission.application.port.out.persistence.FindPermissionsByIdIn;
import com.sprintforge.identity.permission.domain.Permission;
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

import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UpdateRoleDetailImpl implements UpdateRoleDetail {

    private final FindRoleById findRoleById;
    private final ExistsRoleByNameAndIdNot existsRoleByNameAndIdNot;
    private final FindPermissionsByIdIn findPermissionsByIdIn;
    private final SaveRole saveRole;

    @Override
    public Role handle(UpdateRoleDetailCommand command) {
        Role role = findRoleById.findById(command.id()).orElseThrow(
                () -> RoleNotFoundException.byId(command.id())
        );

        if (existsRoleByNameAndIdNot.existsByNameAndIdNot(command.name(), command.id())) {
            throw new DuplicateRoleException(command.name());
        }

        Set<Permission> permissions = findPermissionsByIdIn.findByIdIn(command.permissionIds());

        role.updateDetails(command.name(), command.description());
        role.setPermissions(permissions);
        Role roleSaved = saveRole.save(role);
        return roleSaved;
    }
}
