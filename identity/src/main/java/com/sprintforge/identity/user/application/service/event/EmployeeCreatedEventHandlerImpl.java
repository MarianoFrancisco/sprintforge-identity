package com.sprintforge.identity.user.application.service.event;

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

    private final SaveUser saveUser;

    @Override
    public void handle(EmployeeCreatedIntegrationEvent event) {
        User user = UserMapper.toDomain(event);
        User savedUser = saveUser.save(user);
    }
}
