package com.sprintforge.identity.user.infrastructure.adapter.in.messaging.kafka.employee.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprintforge.identity.user.application.port.in.event.employee.EmployeeRetiredEventHandler;
import com.sprintforge.identity.user.application.port.in.event.employee.EmployeeRetiredIntegrationEvent;
import com.sprintforge.identity.user.infrastructure.adapter.in.messaging.kafka.employee.event.EmployeeRetiredKafkaMessage;
import com.sprintforge.identity.user.infrastructure.adapter.in.messaging.kafka.employee.mapper.EmployeeRetiredKafkaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmployeeRetiredKafkaListener {

    private final EmployeeRetiredEventHandler handler;
    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "#{@kafkaTopicsProperties.employeeRetired}",
            groupId = "#{@kafkaGroupsProperties.defaultGroup}"
    )
    public void listen(String payload) {
        try {
            log.debug("Received EmployeeRetired raw payload: {}", payload);

            EmployeeRetiredKafkaMessage message =
                    objectMapper.readValue(payload, EmployeeRetiredKafkaMessage.class);

            log.debug("Parsed EmployeeRetired event: {}", message);

            EmployeeRetiredIntegrationEvent event = EmployeeRetiredKafkaMapper.toEvent(message);
            handler.handle(event);

        } catch (JsonProcessingException ex) {
            log.warn("Skipping invalid EmployeeRetired payload: {}", payload, ex);
        }
    }
}
