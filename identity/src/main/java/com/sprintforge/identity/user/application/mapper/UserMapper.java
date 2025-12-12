package com.sprintforge.identity.user.application.mapper;

import com.sprintforge.identity.user.application.port.in.event.employeecreated.EmployeeCreatedIntegrationEvent;
import com.sprintforge.identity.user.domain.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    public User toDomain(
            EmployeeCreatedIntegrationEvent event
    ) {
        return new User(
                event.cui(),
                event.email(),
                event.employeeId()
        );
    }
}
