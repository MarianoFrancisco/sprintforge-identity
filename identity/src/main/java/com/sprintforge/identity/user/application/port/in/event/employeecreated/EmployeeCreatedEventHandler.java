package com.sprintforge.identity.user.application.port.in.event.employeecreated;

public interface EmployeeCreatedEventHandler {
    void handle(EmployeeCreatedIntegrationEvent event);
}
