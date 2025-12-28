package com.sprintforge.identity.role.application.port.in.query;

public record GetAllRolesQuery(
        String searchTerm,
        Boolean isActive,
        Boolean isDeleted
) {
}
