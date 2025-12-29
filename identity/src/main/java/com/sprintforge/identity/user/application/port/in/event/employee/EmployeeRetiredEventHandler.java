package com.sprintforge.identity.user.application.port.in.event.employee;

public interface EmployeeRetiredEventHandler {
    void handle(EmployeeRetiredIntegrationEvent event);
}
