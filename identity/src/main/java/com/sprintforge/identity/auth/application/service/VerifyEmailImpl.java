package com.sprintforge.identity.auth.application.service;

import com.sprintforge.identity.auth.application.port.in.command.VerifyEmail;
import com.sprintforge.identity.auth.application.port.in.command.VerifyEmailCommand;
import com.sprintforge.identity.user.application.port.in.query.GetUserById;
import com.sprintforge.identity.user.application.port.in.query.GetUserByIdQuery;
import com.sprintforge.identity.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class VerifyEmailImpl implements VerifyEmail {

    private final GetUserById getUserById;

    @Override
    public void handle(VerifyEmailCommand command) {
        User user = getUserById.handle(new GetUserByIdQuery(UUID.fromString(command.token())));
        user.markEmailAsVerified();
    }
}
