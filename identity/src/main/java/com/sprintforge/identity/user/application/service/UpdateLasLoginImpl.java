package com.sprintforge.identity.user.application.service;

import com.sprintforge.identity.user.application.exception.UserNotFoundException;
import com.sprintforge.identity.user.application.port.in.command.UpdateUserLastLogin;
import com.sprintforge.identity.user.application.port.in.command.UpdateUserLastLoginCommand;
import com.sprintforge.identity.user.application.port.out.persistence.FindUserById;
import com.sprintforge.identity.user.application.port.out.persistence.SaveUser;
import com.sprintforge.identity.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UpdateLasLoginImpl implements UpdateUserLastLogin {

    private final FindUserById findUserById;
    private final SaveUser saveUser;

    @Override
    public void handle(UpdateUserLastLoginCommand command) {
        User user = findUserById.findById(command.id()).orElseThrow(
                () -> UserNotFoundException.byId(command.id())
        );
        user.updateLastLogin();
        saveUser.save(user);
    }
}
