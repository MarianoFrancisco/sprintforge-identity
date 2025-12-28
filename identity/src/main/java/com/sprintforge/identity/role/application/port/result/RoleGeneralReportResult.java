package com.sprintforge.identity.role.application.port.result;

import java.util.List;

public record RoleGeneralReportResult(
        List<RoleBlock> roles,
        long totalRoles,
        long activeRoles
) {
}
