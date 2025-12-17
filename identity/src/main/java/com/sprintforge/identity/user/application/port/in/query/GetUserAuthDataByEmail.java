package com.sprintforge.identity.user.application.port.in.query;

import com.sprintforge.identity.user.application.port.in.result.GetUserAuthDataByEmailResult;

import java.util.Optional;

public interface GetUserAuthDataByEmail {
    Optional<GetUserAuthDataByEmailResult> handle(GetUserAuthDataByEmailQuery query);
}
