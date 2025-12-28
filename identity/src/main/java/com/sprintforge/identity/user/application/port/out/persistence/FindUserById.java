package com.sprintforge.identity.user.application.port.out.persistence;

import com.sprintforge.identity.user.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface FindUserById {
    Optional<User> findById(UUID id);
}
