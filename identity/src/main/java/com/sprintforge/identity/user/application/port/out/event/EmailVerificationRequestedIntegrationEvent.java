package com.sprintforge.identity.user.application.port.out.event;

public record EmailVerificationRequestedIntegrationEvent(
        String email,
        String token
) {
}
