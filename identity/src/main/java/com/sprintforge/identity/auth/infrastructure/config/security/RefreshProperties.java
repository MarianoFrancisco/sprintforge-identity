package com.sprintforge.identity.auth.infrastructure.config.security;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "security.refresh")
public class RefreshProperties {

    @Min(1)
    private long ttlDays = 30;
}
