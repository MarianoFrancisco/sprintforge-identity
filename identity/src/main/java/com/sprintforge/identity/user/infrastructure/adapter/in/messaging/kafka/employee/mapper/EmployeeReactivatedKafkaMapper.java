package com.sprintforge.identity.user.infrastructure.adapter.in.messaging.kafka.employee.mapper;

import com.sprintforge.identity.user.application.port.in.event.employee.EmployeeReactivatedIntegrationEvent;
import com.sprintforge.identity.user.infrastructure.adapter.in.messaging.kafka.employee.event.EmployeeReactivatedKafkaMessage;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EmployeeReactivatedKafkaMapper {
    public EmployeeReactivatedIntegrationEvent toEvent(
            EmployeeReactivatedKafkaMessage message
    ) {
        return new EmployeeReactivatedIntegrationEvent(
                message.employeeId(),
                message.email()
        );
    }
}
