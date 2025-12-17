package com.sprintforge.identity.user.application.port.in.command;

import com.sprintforge.identity.user.domain.User;

public interface UpdateUserRole {
    User handle(UpdateUserRoleCommand command);
}
