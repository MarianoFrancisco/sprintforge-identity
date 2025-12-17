package com.sprintforge.identity.auth.infrastructure.config.security;

import com.sprintforge.identity.auth.application.policy.TokenPolicy;
import com.sprintforge.identity.auth.infrastructure.policy.DefaultTokenPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenPolicyConfig {

    @Bean
    public TokenPolicy tokenPolicy(
            JwtProperties jwtProperties,
            RefreshProperties refreshProperties
    ) {
        return new DefaultTokenPolicy(jwtProperties, refreshProperties);
    }
}
