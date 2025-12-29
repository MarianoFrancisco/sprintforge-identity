package com.sprintforge.identity.user.application.port.out.event;

public interface NotificationEventPublisher {
    void publishEmailVerificationRequested(EmailVerificationRequestedIntegrationEvent event);
}
