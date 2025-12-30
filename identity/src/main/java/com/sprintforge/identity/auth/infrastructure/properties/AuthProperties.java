package com.sprintforge.identity.auth.infrastructure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "auth.set-initial-password")
@Getter
@Setter
public class AuthProperties {

    private String baseUrl;
    private String endpoint;

    public String buildInitialPasswordUrl(String token) {
        return baseUrl + endpoint + "/" + token;
    }
}
