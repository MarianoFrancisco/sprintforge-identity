package com.sprintforge.identity.user.infrastructure.adapter.in.messaging.kafka.employee.mapper;

import com.sprintforge.identity.user.application.port.in.event.employee.EmployeeSuspendedIntegrationEvent;
import com.sprintforge.identity.user.infrastructure.adapter.in.messaging.kafka.employee.event.EmployeeSuspendedKafkaMessage;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EmployeeSuspendedKafkaMapper {
    public EmployeeSuspendedIntegrationEvent toEvent(
            EmployeeSuspendedKafkaMessage message
    ) {
        return new EmployeeSuspendedIntegrationEvent(
                message.employeeId(),
                message.email()
        );
    }
}
