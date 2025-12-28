package com.sprintforge.identity.user.application.port.in.command;

public interface UpdateUserLastLogin {
    void handle(UpdateUserLastLoginCommand command);
}
