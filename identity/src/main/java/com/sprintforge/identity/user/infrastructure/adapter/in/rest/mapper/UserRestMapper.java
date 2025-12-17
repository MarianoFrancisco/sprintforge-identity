package com.sprintforge.identity.user.infrastructure.adapter.in.rest.mapper;

import com.sprintforge.identity.role.infrastructure.adapter.in.rest.mapper.RoleRestMapper;
import com.sprintforge.identity.user.application.port.in.command.UpdateUserRoleCommand;
import com.sprintforge.identity.user.application.port.in.query.GetAllUsersQuery;
import com.sprintforge.identity.user.application.port.in.query.GetUserByIdQuery;
import com.sprintforge.identity.user.domain.User;
import com.sprintforge.identity.user.infrastructure.adapter.in.rest.dto.UpdateUserRoleRequestDTO;
import com.sprintforge.identity.user.infrastructure.adapter.in.rest.dto.UserResponseDTO;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class UserRestMapper {
    public GetAllUsersQuery toQuery(
            String searchTerm,
            String status,
            Boolean isDeleted
    ) {
        return new GetAllUsersQuery(
                searchTerm,
                status,
                isDeleted
        );
    }

    public UserResponseDTO toResponse(User user) {

        String password = user.getPassword() != null
                ? user.getPassword().value()
                : null;

        return new UserResponseDTO(
                user.getId().value(),
                user.getUsername().value(),
                user.getEmail().value(),
                password,
                user.getEmployeeId().value(),
                RoleRestMapper.toResponse(user.getRole()),
                user.getStatus(),
                user.getLastLoginAt(),
                user.getEmailVerifiedAt(),
                user.isMfaEnabled(),
                user.getMfaSecret(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public GetUserByIdQuery toQueryById(UUID id) {
        return new GetUserByIdQuery(id);
    }

    public UpdateUserRoleCommand toUpdateRoleCommand(
            UUID id,
            UpdateUserRoleRequestDTO dto
    ) {
        return new UpdateUserRoleCommand(
                id,
                dto.roleId()
        );
    }
}
