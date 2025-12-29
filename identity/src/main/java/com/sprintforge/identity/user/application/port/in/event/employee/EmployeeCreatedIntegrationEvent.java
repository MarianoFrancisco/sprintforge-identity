package com.sprintforge.identity.user.application.port.in.event.employee;

import java.util.UUID;

public record EmployeeCreatedIntegrationEvent(
        UUID employeeId,
        String cui,
        String email
) {
}
