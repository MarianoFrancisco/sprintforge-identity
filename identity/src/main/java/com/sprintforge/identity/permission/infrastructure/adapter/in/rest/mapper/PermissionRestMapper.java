package com.sprintforge.identity.permission.infrastructure.adapter.in.rest.mapper;

import com.sprintforge.identity.permission.application.port.in.query.GetAllPermissionsQuery;
import com.sprintforge.identity.permission.application.port.in.query.GetPermissionByCodeQuery;
import com.sprintforge.identity.permission.application.port.in.query.GetPermissionByIdQuery;
import com.sprintforge.identity.permission.domain.Permission;
import com.sprintforge.identity.permission.infrastructure.adapter.in.rest.dto.PermissionResponseDTO;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.UUID;

@UtilityClass
public class PermissionRestMapper {

    public PermissionResponseDTO toResponse(Permission permission) {
        return new PermissionResponseDTO(
                permission.getId().value(),
                permission.getCode().value(),
                permission.getName().value(),
                permission.getDescription().value(),
                permission.getCategory().value()
        );
    }

    public GetAllPermissionsQuery toQuery(
            String searchTerm,
            List<UUID> ids
    ) {
        return new GetAllPermissionsQuery(
                searchTerm,
                ids
        );
    }

    public GetPermissionByIdQuery toQueryById(
            UUID id
    ) {
        return new GetPermissionByIdQuery(
                id
        );
    }

    public GetPermissionByCodeQuery toQueryByCode(
            String code
    ) {
        return new GetPermissionByCodeQuery(
                code
        );
    }
}
