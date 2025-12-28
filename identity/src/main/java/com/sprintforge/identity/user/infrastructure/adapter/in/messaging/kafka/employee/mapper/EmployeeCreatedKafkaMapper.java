package com.sprintforge.identity.user.infrastructure.adapter.in.messaging.kafka.employee.mapper;

import com.sprintforge.common.infrastructure.adapter.in.messaging.kafka.employee.event.EmployeeCreatedKafkaMessage;
import com.sprintforge.identity.user.application.port.in.event.employeecreated.EmployeeCreatedIntegrationEvent;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EmployeeCreatedKafkaMapper {
    public EmployeeCreatedIntegrationEvent toEvent(
            EmployeeCreatedKafkaMessage message
    ) {
        return new EmployeeCreatedIntegrationEvent(
                message.employeeId(),
                message.cui(),
                message.email()
        );
    }
}
