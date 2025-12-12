package com.sprintforge.identity.user.application.service;

import com.sprintforge.identity.user.application.exception.UserNotFoundException;
import com.sprintforge.identity.user.application.port.in.query.GetUserById;
import com.sprintforge.identity.user.application.port.in.query.GetUserByIdQuery;
import com.sprintforge.identity.user.application.port.out.persistence.FindUserById;
import com.sprintforge.identity.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetUsersByIdImpl implements GetUserById {

    private final FindUserById findUserById;

    @Override
    public User handle(GetUserByIdQuery query) {
        return findUserById.findById(query.id()).orElseThrow(
                () -> UserNotFoundException.byId(query.id())
        );
    }
}
