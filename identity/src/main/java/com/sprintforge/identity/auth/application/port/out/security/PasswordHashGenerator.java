package com.sprintforge.identity.auth.application.port.out.security;

public interface PasswordHashGenerator {
    String generate(String password);
}
