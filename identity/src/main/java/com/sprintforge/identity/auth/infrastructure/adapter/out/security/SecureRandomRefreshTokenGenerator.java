package com.sprintforge.identity.auth.infrastructure.adapter.out.security;

import com.sprintforge.identity.auth.application.port.out.security.RefreshTokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64.Encoder;

import static java.util.Base64.getUrlEncoder;

@Component
@RequiredArgsConstructor
public class SecureRandomRefreshTokenGenerator implements RefreshTokenGenerator {

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Encoder encoder = getUrlEncoder().withoutPadding();

    @Override
    public String generate() {
        byte[] randomBytes = new byte[64];
        secureRandom.nextBytes(randomBytes);
        return encoder.encodeToString(randomBytes);
    }
}
