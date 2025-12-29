package com.sprintforge.identity.user.application.service.event;

import com.sprintforge.identity.user.application.exception.UserNotFoundException;
import com.sprintforge.identity.user.application.port.in.event.employee.EmployeeReactivatedEventHandler;
import com.sprintforge.identity.user.application.port.in.event.employee.EmployeeReactivatedIntegrationEvent;
import com.sprintforge.identity.user.application.port.out.persistence.FindUserByEmail;
import com.sprintforge.identity.user.application.port.out.persistence.SaveUser;
import com.sprintforge.identity.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class EmployeeReactivatedEventHandlerImpl implements EmployeeReactivatedEventHandler {

    private final FindUserByEmail findUserByEmail;
    private final SaveUser saveUser;

    @Override
    public void handle(EmployeeReactivatedIntegrationEvent event) {
        User user = findUserByEmail.findByEmail(event.email())
                .orElseThrow(() -> UserNotFoundException.byEmail(event.email()));
        user.activate();
        saveUser.save(user);
    }
}
