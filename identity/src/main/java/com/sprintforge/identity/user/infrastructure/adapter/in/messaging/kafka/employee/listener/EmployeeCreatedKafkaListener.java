package com.sprintforge.identity.user.infrastructure.adapter.in.messaging.kafka.employee.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprintforge.common.infrastructure.adapter.in.messaging.kafka.employee.event.EmployeeCreatedKafkaMessage;
import com.sprintforge.identity.user.application.port.in.event.employee.EmployeeCreatedEventHandler;
import com.sprintforge.identity.user.application.port.in.event.employee.EmployeeCreatedIntegrationEvent;
import com.sprintforge.identity.user.infrastructure.adapter.in.messaging.kafka.employee.mapper.EmployeeCreatedKafkaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmployeeCreatedKafkaListener {

    private final EmployeeCreatedEventHandler handler;
    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "#{@kafkaTopicsProperties.employeeCreated}",
            groupId = "#{@kafkaGroupsProperties.defaultGroup}"
    )
    public void listen(String payload) {
        try {
            log.debug("Received EmployeeCreated raw payload: {}", payload);

            EmployeeCreatedKafkaMessage message =
                    objectMapper.readValue(payload, EmployeeCreatedKafkaMessage.class);

            log.debug("Parsed EmployeeCreated event: {}", message);

            EmployeeCreatedIntegrationEvent event = EmployeeCreatedKafkaMapper.toEvent(message);
            handler.handle(event);

        } catch (JsonProcessingException ex) {
            log.warn("Skipping invalid EmployeeCreated payload: {}", payload, ex);
        }
    }
}
