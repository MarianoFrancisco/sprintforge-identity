package com.sprintforge.identity.user.application.port.in.event.employee;

public interface EmployeeCreatedEventHandler {
    void handle(EmployeeCreatedIntegrationEvent event);
}
