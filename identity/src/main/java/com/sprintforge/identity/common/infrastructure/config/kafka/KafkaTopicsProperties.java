package com.sprintforge.identity.common.infrastructure.config.kafka;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KafkaTopicsProperties {
    private String employeeCreated;
    private String emailVerificationRequested;
}
