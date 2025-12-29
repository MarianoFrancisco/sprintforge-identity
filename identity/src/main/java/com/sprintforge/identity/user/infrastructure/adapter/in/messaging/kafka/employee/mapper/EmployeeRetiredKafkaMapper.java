package com.sprintforge.identity.user.infrastructure.adapter.in.messaging.kafka.employee.mapper;

import com.sprintforge.identity.user.application.port.in.event.employee.EmployeeRetiredIntegrationEvent;
import com.sprintforge.identity.user.infrastructure.adapter.in.messaging.kafka.employee.event.EmployeeRetiredKafkaMessage;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EmployeeRetiredKafkaMapper {
    public EmployeeRetiredIntegrationEvent toEvent(
            EmployeeRetiredKafkaMessage message
    ) {
        return new EmployeeRetiredIntegrationEvent(
                message.employeeId(),
                message.email()
        );
    }
}
