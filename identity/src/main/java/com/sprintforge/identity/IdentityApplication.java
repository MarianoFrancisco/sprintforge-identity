package com.sprintforge.identity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = "com.sprintforge")
@ConfigurationPropertiesScan(basePackages = "com.sprintforge")
public class IdentityApplication {

    static void main(String[] args) {
        SpringApplication.run(IdentityApplication.class, args);
    }

}
