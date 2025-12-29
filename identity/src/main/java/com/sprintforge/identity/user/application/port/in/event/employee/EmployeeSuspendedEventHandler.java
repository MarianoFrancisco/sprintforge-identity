package com.sprintforge.identity.user.application.port.in.event.employee;

public interface EmployeeSuspendedEventHandler {
    void handle(EmployeeSuspendedIntegrationEvent event);
}
