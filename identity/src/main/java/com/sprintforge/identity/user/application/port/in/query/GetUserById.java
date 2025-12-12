package com.sprintforge.identity.user.application.port.in.query;

import com.sprintforge.identity.user.domain.User;

public interface GetUserById {
    User handle(GetUserByIdQuery query);
}
