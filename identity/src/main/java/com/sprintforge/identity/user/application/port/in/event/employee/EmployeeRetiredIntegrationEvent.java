package com.sprintforge.identity.user.application.port.in.event.employee;

import java.util.UUID;

public record EmployeeRetiredIntegrationEvent(
        UUID employeeId,
        String email
) {
}
