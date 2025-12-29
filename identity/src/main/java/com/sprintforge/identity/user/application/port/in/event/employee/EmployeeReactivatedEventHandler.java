package com.sprintforge.identity.user.application.port.in.event.employee;

public interface EmployeeReactivatedEventHandler {
    void handle(EmployeeReactivatedIntegrationEvent event);
}
