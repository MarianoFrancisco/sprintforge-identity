package com.sprintforge.identity.user.infrastructure.adapter.out.messaging.kafka.mapper;

import com.sprintforge.identity.user.application.port.out.event.EmailVerificationRequestedIntegrationEvent;
import com.sprintforge.identity.user.infrastructure.adapter.out.messaging.kafka.event.EmailVerificationRequestedKafkaMessage;
import lombok.experimental.UtilityClass;

@UtilityClass
public class NotificationKafkaEventMapper {

    public EmailVerificationRequestedKafkaMessage toMessage(EmailVerificationRequestedIntegrationEvent event) {
        return new EmailVerificationRequestedKafkaMessage(
                event.email(),
                event.token()
        );
    }
}
