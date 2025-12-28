package com.sprintforge.identity.auth.infrastructure.adapter.out.security;

import com.sprintforge.identity.auth.application.port.out.security.PasswordVerifier;
import com.sprintforge.identity.auth.application.port.out.security.PasswordHashGenerator;
import com.sprintforge.identity.auth.infrastructure.config.security.PasswordProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BcryptPasswordHasher implements PasswordHashGenerator, PasswordVerifier {

    private final PasswordProperties passwordProperties;

    @Override
    public String generate(String password) {
        return encoder().encode(password);
    }

    @Override
    public boolean matches(String password, String hashedPassword) {
        return encoder().matches(password, hashedPassword);
    }

    private BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder(passwordProperties.getBcryptStrength());
    }
}