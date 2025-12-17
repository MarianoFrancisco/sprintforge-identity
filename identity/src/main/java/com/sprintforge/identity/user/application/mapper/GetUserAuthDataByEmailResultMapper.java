package com.sprintforge.identity.user.application.mapper;

import com.sprintforge.identity.user.application.port.in.result.GetUserAuthDataByEmailResult;
import com.sprintforge.identity.user.domain.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GetUserAuthDataByEmailResultMapper {

    public GetUserAuthDataByEmailResult from(User u) {
        return new GetUserAuthDataByEmailResult(
                u.getId().value(),
                u.getEmployeeId().value(),
                u.getRole().getId().value(),
                u.getEmail().value(),
                u.getPassword().value(),
                u.getStatus().name()
        );
    }
}