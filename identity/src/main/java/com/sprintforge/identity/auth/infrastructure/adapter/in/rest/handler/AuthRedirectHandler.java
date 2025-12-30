package com.sprintforge.identity.auth.infrastructure.adapter.in.rest.handler;


import com.sprintforge.identity.auth.application.exception.PasswordNotSetException;
import com.sprintforge.identity.auth.infrastructure.properties.AuthProperties;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
@RequiredArgsConstructor
public class AuthRedirectHandler {
    private final AuthProperties authProperties;

    @ExceptionHandler(PasswordNotSetException.class)
    public void handlePasswordNotSet(
            PasswordNotSetException ex,
            HttpServletResponse response
    ) throws IOException {
        response.sendRedirect(
                authProperties.buildInitialPasswordUrl(ex.token())
        );
    }
}
