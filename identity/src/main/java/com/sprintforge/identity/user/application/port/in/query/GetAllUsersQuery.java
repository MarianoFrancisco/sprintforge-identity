package com.sprintforge.identity.user.application.port.in.query;

public record GetAllUsersQuery(
        String searchTerm,
        String status,
        Boolean isDeleted
) {
}
