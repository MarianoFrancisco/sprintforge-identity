package com.sprintforge.identity.user.application.port.out.persistence;

import com.sprintforge.identity.user.domain.User;

public interface SaveUser {
    User save(User user);
}
