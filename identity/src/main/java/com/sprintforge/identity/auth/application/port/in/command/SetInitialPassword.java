package com.sprintforge.identity.auth.application.port.in.command;

public interface SetInitialPassword {
    void handle(SetInitialPasswordCommand command);
}
