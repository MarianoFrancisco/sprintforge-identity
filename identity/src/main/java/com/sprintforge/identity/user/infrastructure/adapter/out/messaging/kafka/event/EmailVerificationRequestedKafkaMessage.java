package com.sprintforge.identity.user.infrastructure.adapter.out.messaging.kafka.event;

public record EmailVerificationRequestedKafkaMessage(
        String email,
        String token
) {
}
