package com.sprintforge.identity.user.infrastructure.adapter.in.messaging.kafka.employee.event;

import java.util.UUID;

public record EmployeeReactivatedKafkaMessage(
        UUID employeeId,
        String cui,
        String email,
        String fullName,
        String status
) {
}
