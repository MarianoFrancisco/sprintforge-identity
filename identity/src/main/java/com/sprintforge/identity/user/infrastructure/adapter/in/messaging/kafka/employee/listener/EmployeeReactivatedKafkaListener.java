package com.sprintforge.identity.user.infrastructure.adapter.in.messaging.kafka.employee.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprintforge.identity.user.application.port.in.event.employee.EmployeeReactivatedEventHandler;
import com.sprintforge.identity.user.application.port.in.event.employee.EmployeeReactivatedIntegrationEvent;
import com.sprintforge.identity.user.infrastructure.adapter.in.messaging.kafka.employee.event.EmployeeReactivatedKafkaMessage;
import com.sprintforge.identity.user.infrastructure.adapter.in.messaging.kafka.employee.mapper.EmployeeReactivatedKafkaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmployeeReactivatedKafkaListener {

    private final EmployeeReactivatedEventHandler handler;
    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "#{@kafkaTopicsProperties.employeeReactivated}",
            groupId = "#{@kafkaGroupsProperties.defaultGroup}"
    )
    public void listen(String payload) {
        try {
            log.debug("Received EmployeeReactivated raw payload: {}", payload);

            EmployeeReactivatedKafkaMessage message =
                    objectMapper.readValue(payload, EmployeeReactivatedKafkaMessage.class);

            log.debug("Parsed EmployeeReactivated event: {}", message);

            EmployeeReactivatedIntegrationEvent event = EmployeeReactivatedKafkaMapper.toEvent(message);
            handler.handle(event);

        } catch (JsonProcessingException ex) {
            log.warn("Skipping invalid EmployeeReactivated payload: {}", payload, ex);
        }
    }
}
