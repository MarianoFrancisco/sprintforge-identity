package com.sprintforge.identity.role.application.port.result;

import java.util.List;
import java.util.UUID;

public record RoleBlock(
        UUID roleId,
        String roleName,
        boolean roleActive,

        long activeUsersCount,
        long inactiveUsersCount,

        List<RoleUserRow> activeUsers,
        List<RoleUserRow> inactiveUsers
) {
}