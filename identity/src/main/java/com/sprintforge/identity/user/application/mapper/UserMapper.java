package com.sprintforge.identity.user.application.mapper;

import com.sprintforge.identity.role.domain.Role;
import com.sprintforge.identity.user.application.port.in.event.employee.EmployeeCreatedIntegrationEvent;
import com.sprintforge.identity.user.domain.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    public User toDomain(
            EmployeeCreatedIntegrationEvent event,
            Role role
    ) {
        return new User(
                event.cui(),
                event.email(),
                event.employeeId(),
                role
        );
    }
}
