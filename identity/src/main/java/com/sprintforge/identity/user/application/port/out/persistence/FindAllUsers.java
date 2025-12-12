package com.sprintforge.identity.user.application.port.out.persistence;

import com.sprintforge.identity.user.application.port.in.query.GetAllUsersQuery;
import com.sprintforge.identity.user.domain.User;

import java.util.List;

public interface FindAllUsers {
    List<User> findAll(GetAllUsersQuery query);
}
