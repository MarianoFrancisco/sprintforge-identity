package com.sprintforge.identity.user.infrastructure.adapter.out.messaging.kafka;

import com.sprintforge.identity.common.infrastructure.config.kafka.KafkaTopicsProperties;
import com.sprintforge.identity.user.application.port.out.event.EmailVerificationRequestedIntegrationEvent;
import com.sprintforge.identity.user.application.port.out.event.NotificationEventPublisher;
import com.sprintforge.identity.user.infrastructure.adapter.out.messaging.kafka.event.EmailVerificationRequestedKafkaMessage;
import com.sprintforge.identity.user.infrastructure.adapter.out.messaging.kafka.mapper.NotificationKafkaEventMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NullMarked;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static java.util.UUID.randomUUID;

@Slf4j
@NullMarked
@Component
@RequiredArgsConstructor
public class NotificationKafkaEventPublisher implements NotificationEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final KafkaTopicsProperties topics;

    @Override
    public void publishEmailVerificationRequested(
            EmailVerificationRequestedIntegrationEvent event
    ) {
        EmailVerificationRequestedKafkaMessage message = NotificationKafkaEventMapper.toMessage(event);
        String topic = topics.getEmailVerificationRequested();
        String key = randomUUID().toString();

        kafkaTemplate.send(topic, key, message);

        log.debug("Published EmailVerificationRequested event. topic={}, key={}", topic, key);
    }
}
