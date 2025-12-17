package com.sprintforge.identity.auth.application.port.out.persistence;

import com.sprintforge.identity.auth.domain.AuthSession;

public interface SaveAuthSession {
    AuthSession save(AuthSession authSession);
}
