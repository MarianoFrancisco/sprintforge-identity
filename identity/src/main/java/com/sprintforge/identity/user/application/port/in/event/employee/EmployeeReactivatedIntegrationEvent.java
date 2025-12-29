package com.sprintforge.identity.user.application.port.in.event.employee;

import java.util.UUID;

public record EmployeeReactivatedIntegrationEvent(
        UUID employeeId,
        String email
) {
}
