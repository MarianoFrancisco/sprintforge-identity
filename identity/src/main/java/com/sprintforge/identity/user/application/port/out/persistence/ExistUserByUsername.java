package com.sprintforge.identity.user.application.port.out.persistence;

public interface ExistUserByUsername {
    boolean existsByUsername(String username);
}
