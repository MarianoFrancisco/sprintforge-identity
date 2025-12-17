package com.sprintforge.identity.auth.application.port.out.security;

public interface PasswordVerifier {
    boolean matches(String password, String hashedPassword);
}
