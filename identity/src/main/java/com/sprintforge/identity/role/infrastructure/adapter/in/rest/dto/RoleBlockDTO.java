package com.sprintforge.identity.role.infrastructure.adapter.in.rest.dto;

import java.util.List;
import java.util.UUID;

public record RoleBlockDTO(
        UUID roleId,
        String roleName,
        boolean roleActive,

        long activeUsersCount,
        long inactiveUsersCount,

        List<RoleUserRowDTO> activeUsers,
        List<RoleUserRowDTO> inactiveUsers
) {
}