package com.sprintforge.identity.auth.application.service;

import com.sprintforge.identity.auth.application.port.in.command.SetInitialPassword;
import com.sprintforge.identity.auth.application.port.in.command.SetInitialPasswordCommand;
import com.sprintforge.identity.auth.application.port.out.security.PasswordHashGenerator;
import com.sprintforge.identity.user.application.port.in.command.SetInitialUserPassword;
import com.sprintforge.identity.user.application.port.in.command.SetInitialUserPasswordCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SetInitialPasswordImpl implements SetInitialPassword {

    private final PasswordHashGenerator passwordHashGenerator;
    private final SetInitialUserPassword setInitialUserPassword;

    @Override
    public void handle(SetInitialPasswordCommand command) {
        String initialPassword = passwordHashGenerator.generate(command.initialPassword());
        setInitialUserPassword.handle(
                new SetInitialUserPasswordCommand(
                        command.userId(),
                        initialPassword
                )
        );
    }
}
