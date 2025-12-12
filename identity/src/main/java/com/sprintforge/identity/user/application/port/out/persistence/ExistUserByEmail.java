package com.sprintforge.identity.user.application.port.out.persistence;

public interface ExistUserByEmail {
    boolean existsByEmail(String email);
}
