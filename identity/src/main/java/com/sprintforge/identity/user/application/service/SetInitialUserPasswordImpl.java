package com.sprintforge.identity.user.application.service;

import com.sprintforge.identity.user.application.exception.AlreadySetInitialUserPassword;
import com.sprintforge.identity.user.application.exception.UserNotFoundException;
import com.sprintforge.identity.user.application.port.in.command.SetInitialUserPassword;
import com.sprintforge.identity.user.application.port.in.command.SetInitialUserPasswordCommand;
import com.sprintforge.identity.user.application.port.out.persistence.FindUserById;
import com.sprintforge.identity.user.application.port.out.persistence.SaveUser;
import com.sprintforge.identity.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SetInitialUserPasswordImpl implements SetInitialUserPassword {

    private final FindUserById findUserById;
    private final SaveUser saveUser;

    @Override
    public void handle(SetInitialUserPasswordCommand command) {
        User user = findUserById.findById(command.id()).orElseThrow(
                () -> UserNotFoundException.byId(command.id())
        );
        if (!user.getPassword().isEmpty()) {
            throw new AlreadySetInitialUserPassword();
        }
        user.setInitialPassword(command.initialPassword());
        saveUser.save(user);
    }
}
