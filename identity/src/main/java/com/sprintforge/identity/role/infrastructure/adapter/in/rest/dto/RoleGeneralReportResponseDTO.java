package com.sprintforge.identity.role.infrastructure.adapter.in.rest.dto;

import java.util.List;

public record RoleGeneralReportResponseDTO(
        List<RoleBlockDTO> roles,
        long totalRoles,
        long activeRoles
) {
}
