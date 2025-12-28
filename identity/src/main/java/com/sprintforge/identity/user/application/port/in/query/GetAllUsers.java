package com.sprintforge.identity.user.application.port.in.query;

import com.sprintforge.identity.user.domain.User;

import java.util.List;

public interface GetAllUsers {
    List<User> handle(GetAllUsersQuery query);
}
