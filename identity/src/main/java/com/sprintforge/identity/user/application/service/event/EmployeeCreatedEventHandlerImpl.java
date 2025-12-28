package com.sprintforge.identity.user.application.service.event;

import com.sprintforge.identity.role.application.port.in.query.GetRoleByIsDefaultTrue;
import com.sprintforge.identity.role.domain.Role;
import com.sprintforge.identity.user.application.mapper.UserMapper;
import com.sprintforge.identity.user.application.port.in.event.employeecreated.EmployeeCreatedEventHandler;
import com.sprintforge.identity.user.application.port.in.event.employeecreated.EmployeeCreatedIntegrationEvent;
import com.sprintforge.identity.user.application.port.out.persistence.SaveUser;
import com.sprintforge.identity.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class EmployeeCreatedEventHandlerImpl implements EmployeeCreatedEventHandler {

    private final GetRoleByIsDefaultTrue getDefaultRole;
    private final SaveUser saveUser;

    @Override
    public void handle(EmployeeCreatedIntegrationEvent event) {
        Role role = getDefaultRole.handle();
        User user = UserMapper.toDomain(event, role);
        User savedUser = saveUser.save(user);
    }
}
