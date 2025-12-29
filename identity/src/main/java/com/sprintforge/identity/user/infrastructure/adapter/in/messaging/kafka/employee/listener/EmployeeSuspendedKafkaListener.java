package com.sprintforge.identity.user.infrastructure.adapter.in.messaging.kafka.employee.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprintforge.identity.user.application.port.in.event.employee.EmployeeSuspendedEventHandler;
import com.sprintforge.identity.user.application.port.in.event.employee.EmployeeSuspendedIntegrationEvent;
import com.sprintforge.identity.user.infrastructure.adapter.in.messaging.kafka.employee.event.EmployeeSuspendedKafkaMessage;
import com.sprintforge.identity.user.infrastructure.adapter.in.messaging.kafka.employee.mapper.EmployeeSuspendedKafkaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmployeeSuspendedKafkaListener {

    private final EmployeeSuspendedEventHandler handler;
    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "#{@kafkaTopicsProperties.employeeSuspended}",
            groupId = "#{@kafkaGroupsProperties.defaultGroup}"
    )
    public void listen(String payload) {
        try {
            log.debug("Received EmployeeSuspended raw payload: {}", payload);

            EmployeeSuspendedKafkaMessage message =
                    objectMapper.readValue(payload, EmployeeSuspendedKafkaMessage.class);

            log.debug("Parsed EmployeeSuspended event: {}", message);

            EmployeeSuspendedIntegrationEvent event = EmployeeSuspendedKafkaMapper.toEvent(message);
            handler.handle(event);

        } catch (JsonProcessingException ex) {
            log.warn("Skipping invalid EmployeeSuspended payload: {}", payload, ex);
        }
    }
}
