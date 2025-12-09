package com.sprintforge.identity.role.infrastructure.adapter.in.rest.mapper;

import com.sprintforge.identity.role.application.port.in.command.*;
import com.sprintforge.identity.role.application.port.in.query.GetAllRolesQuery;
import com.sprintforge.identity.role.application.port.in.query.GetRoleByIdQuery;
import com.sprintforge.identity.role.infrastructure.adapter.in.rest.dto.CreateRoleRequestDTO;
import com.sprintforge.identity.role.infrastructure.adapter.in.rest.dto.RoleResponseDTO;
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
                dto.isDefault()
        );
    }

    public RoleResponseDTO toResponse(com.sprintforge.identity.role.domain.Role role) {
        return new RoleResponseDTO(
                role.getId().value(),
                role.getName().value(),
                role.getDescription().value(),
                role.isDefault(),
                role.isActive(),
                role.isDeleted(),
                role.getCreatedAt(),
                role.getUpdatedAt()
        );
    }

    public UpdateRoleDetailCommand toUpdateCommand(
            UUID id,
            String name,
            String description,
            Boolean isDefault
    ) {
        return new UpdateRoleDetailCommand(
                id,
                name,
                description,
                isDefault
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
