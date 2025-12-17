package com.sprintforge.identity.auth.application.service;

import com.sprintforge.identity.auth.application.port.in.command.ChangePassword;
import com.sprintforge.identity.auth.application.port.in.command.ChangePasswordCommand;
import com.sprintforge.identity.auth.application.port.out.security.PasswordHashGenerator;
import com.sprintforge.identity.auth.application.port.out.security.PasswordVerifier;
import com.sprintforge.identity.user.application.port.in.command.UpdateUserPassword;
import com.sprintforge.identity.user.application.port.in.command.UpdateUserPasswordCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ChangePasswordImpl implements ChangePassword {

    private final PasswordVerifier passwordVerifier;
    private final PasswordHashGenerator passwordHashGenerator;
    private final UpdateUserPassword updateUserPassword;

    @Override
    public void handle(ChangePasswordCommand command) {
        passwordVerifier.matches(
                command.currentPassword(),
                command.newPassword()
        );
        String newPassword = passwordHashGenerator.generate(command.newPassword());
        updateUserPassword.handle(
                new UpdateUserPasswordCommand(
                        command.userId(),
                        newPassword
                )
        );
    }
}
