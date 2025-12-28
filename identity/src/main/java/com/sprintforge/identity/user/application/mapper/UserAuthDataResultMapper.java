package com.sprintforge.identity.user.application.mapper;

import com.sprintforge.identity.user.application.port.in.result.UserAuthDataResult;
import com.sprintforge.identity.user.domain.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserAuthDataResultMapper {

    public UserAuthDataResult from(User u) {
        return new UserAuthDataResult(
                u.getId().value(),
                u.getEmployeeId().value(),
                u.getRole().getId().value(),
                u.getEmail().value(),
                u.getPassword().value(),
                u.getStatus().name()
        );
    }
}