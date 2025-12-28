package com.sprintforge.identity.auth.application.port.in.command;

import com.sprintforge.identity.auth.application.port.in.result.TokenPairResult;

public interface Login {
    TokenPairResult handle(LoginCommand command);
}
