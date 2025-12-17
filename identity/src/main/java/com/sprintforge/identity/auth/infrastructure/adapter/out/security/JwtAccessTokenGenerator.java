package com.sprintforge.identity.auth.infrastructure.adapter.out.security;

import com.sprintforge.identity.auth.application.port.out.security.AccessTokenGenerator;
import com.sprintforge.identity.auth.infrastructure.config.security.JwtProperties;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.UUID;

import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Date.from;

@Component
@RequiredArgsConstructor
public class JwtAccessTokenGenerator implements AccessTokenGenerator {

    private final JwtProperties props;

    @Override
    public String generate(
            UUID userId,
            UUID roleId,
            Instant issuedAt,
            Instant expiresAt,
            UUID employeeId,
            UUID authSessionId
    ) {
        Key key = hmacShaKeyFor(props.getSecret().getBytes(UTF_8));

        return Jwts.builder()
                .subject(userId.toString())
                .issuer(props.getIssuer())
                .issuedAt(from(issuedAt))
                .expiration(from(expiresAt))
                .claim("rid", roleId)
                .claim("eid", employeeId)
                .claim("asid", authSessionId)
                .signWith(key)
                .compact();
    }
}
