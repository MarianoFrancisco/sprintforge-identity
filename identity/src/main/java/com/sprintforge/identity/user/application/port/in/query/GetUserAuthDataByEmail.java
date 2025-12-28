package com.sprintforge.identity.user.application.port.in.query;

import com.sprintforge.identity.user.application.port.in.result.UserAuthDataResult;

import java.util.Optional;

public interface GetUserAuthDataByEmail {
    Optional<UserAuthDataResult> handle(GetUserAuthDataByEmailQuery query);
}
