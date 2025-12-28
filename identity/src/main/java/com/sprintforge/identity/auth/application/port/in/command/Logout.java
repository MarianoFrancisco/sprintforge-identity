package com.sprintforge.identity.auth.application.port.in.command;

public interface Logout {
    void handle(LogoutCommand command);
}
