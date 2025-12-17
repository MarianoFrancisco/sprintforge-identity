package com.sprintforge.identity.user.application.port.out.persistence;

import com.sprintforge.identity.user.domain.User;

import java.util.Optional;

public interface FindUserByEmail {
    Optional<User> findByEmail(String email);
}
