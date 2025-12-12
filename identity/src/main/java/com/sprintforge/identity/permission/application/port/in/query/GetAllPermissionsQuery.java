package com.sprintforge.identity.permission.application.port.in.query;

import java.util.List;
import java.util.UUID;

public record GetAllPermissionsQuery(
        String searchTerm,
        List<UUID> ids
) {
}
