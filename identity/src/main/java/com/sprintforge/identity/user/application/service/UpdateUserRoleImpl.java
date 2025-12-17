package com.sprintforge.identity.user.application.service;

import com.sprintforge.identity.role.application.port.in.query.GetRoleById;
import com.sprintforge.identity.role.application.port.in.query.GetRoleByIdQuery;
import com.sprintforge.identity.role.domain.Role;
import com.sprintforge.identity.user.application.exception.UserNotFoundException;
import com.sprintforge.identity.user.application.port.in.command.UpdateUserRole;
import com.sprintforge.identity.user.application.port.in.command.UpdateUserRoleCommand;
import com.sprintforge.identity.user.application.port.out.persistence.FindUserById;
import com.sprintforge.identity.user.application.port.out.persistence.SaveUser;
import com.sprintforge.identity.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UpdateUserRoleImpl implements UpdateUserRole {

    private final FindUserById findUserById;
    private final GetRoleById getRoleById;
    private final SaveUser saveUser;

    @Override
    public User handle(UpdateUserRoleCommand command) {
        User user = findUserById.findById(command.id()).orElseThrow(
                () -> UserNotFoundException.byId(command.id())
        );

        this.changeUserRole(user, command);
        User savedUser = saveUser.save(user);

        return savedUser;
    }

    private void changeUserRole(User user, UpdateUserRoleCommand command) {
        Role role = getRoleById.handle(
                new GetRoleByIdQuery(
                        command.roleId()
                )
        );
        user.changeRole(role);
    }
}
