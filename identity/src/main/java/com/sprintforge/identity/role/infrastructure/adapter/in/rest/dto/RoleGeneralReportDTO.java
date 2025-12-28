package com.sprintforge.identity.role.infrastructure.adapter.in.rest.dto;

import java.util.List;

public record RoleGeneralReportDTO(
        List<RoleBlockDTO> roles,
        long totalRoles,
        long activeRoles
) {
}
