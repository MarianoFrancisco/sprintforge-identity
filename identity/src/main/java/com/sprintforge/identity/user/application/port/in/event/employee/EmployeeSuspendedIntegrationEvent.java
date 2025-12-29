package com.sprintforge.identity.user.application.port.in.event.employee;

import java.util.UUID;

public record EmployeeSuspendedIntegrationEvent(
        UUID employeeId,
        String email
) {
}
