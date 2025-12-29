package com.sprintforge.identity.common.infrastructure.config.kafka;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KafkaTopicsProperties {
    private String employeeCreated;
    String employeeRetired;
    String employeeSuspended;
    String employeeReactivated;
    private String emailVerificationRequested;
}
