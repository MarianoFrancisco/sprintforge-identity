package com.sprintforge.identity.role.infrastructure.adapter.in.rest.mapper;

import com.sprintforge.identity.role.application.port.in.command.*;
import com.sprintforge.identity.role.application.port.in.query.GetAllRolesQuery;
import com.sprintforge.identity.role.application.port.in.query.GetRoleByIdQuery;
import com.sprintforge.identity.role.domain.Role;
import com.sprintforge.identity.role.infrastructure.adapter.in.rest.dto.CreateRoleRequestDTO;
import com.sprintforge.identity.role.infrastructure.adapter.in.rest.dto.RoleResponseDTO;
import com.sprintforge.identity.role.infrastructure.adapter.in.rest.dto.UpdateRoleRequestDTO;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class RoleRestMapper {
    public GetAllRolesQuery toQuery(String searchTerm, Boolean isActive, Boolean isDeleted) {
        return new GetAllRolesQuery(searchTerm, isActive, isDeleted);
    }

    public GetRoleByIdQuery toQuery(UUID id) {
        return new GetRoleByIdQuery(id);
    }

    public CreateRoleCommand toCreateCommand(CreateRoleRequestDTO dto) {
        return new CreateRoleCommand(
                dto.name(),
                dto.description(),
                dto.permissionIds()
        );
    }

    public RoleResponseDTO toResponse(Role role) {
        return new RoleResponseDTO(
                role.getId().value(),
                role.getName().value(),
                role.getDescription().value(),
                role.isDefault(),
                role.isActive(),
                role.isDeleted(),
                role.getCreatedAt(),
                role.getUpdatedAt(),
                role.getPermissionIds()
        );
    }

    public UpdateRoleDetailCommand toUpdateCommand(
            UUID id,
            UpdateRoleRequestDTO dto
    ) {
        return new UpdateRoleDetailCommand(
                id,
                dto.name(),
                dto.description(),
                dto.permissionIds()
        );
    }

    public ActivateRoleCommand toActivateCommand(
            UUID id
    ) {
        return new ActivateRoleCommand(id);
    }

    public DeactivateRoleCommand toDeactivateCommand(
            UUID id
    ) {
        return new DeactivateRoleCommand(id);
    }

    public DeleteRoleCommand toDeleteCommand(
            UUID id
    ) {
        return new DeleteRoleCommand(id);
    }
}
