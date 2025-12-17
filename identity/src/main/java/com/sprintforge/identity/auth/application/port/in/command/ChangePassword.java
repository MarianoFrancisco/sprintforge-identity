package com.sprintforge.identity.auth.application.port.in.command;

public interface ChangePassword {
    void handle(ChangePasswordCommand command);
}
