package com.sprintforge.identity.auth.application.service;

import com.sprintforge.identity.auth.application.exception.AuthSessionNotFoundException;
import com.sprintforge.identity.auth.application.port.in.command.Logout;
import com.sprintforge.identity.auth.application.port.in.command.LogoutCommand;
import com.sprintforge.identity.auth.application.port.out.persistence.FindAuthSessionById;
import com.sprintforge.identity.auth.application.port.out.persistence.SaveAuthSession;
import com.sprintforge.identity.auth.domain.AuthSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class LogoutImpl implements Logout {

    private final Clock clock;
    private final FindAuthSessionById findAuthSessionById;
    private final SaveAuthSession saveAuthSession;

    @Override
    public void handle(LogoutCommand command) {
        AuthSession authSession = findAuthSessionById.findById(command.authSessionId())
                .orElseThrow(
                        () -> AuthSessionNotFoundException.byId(command.authSessionId())
                );
        authSession.revoke(
                clock.instant(),
                "Cierre de sesión por el usuario"
        );
        saveAuthSession.save(authSession);
    }
}
