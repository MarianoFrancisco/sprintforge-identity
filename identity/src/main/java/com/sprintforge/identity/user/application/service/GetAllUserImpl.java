package com.sprintforge.identity.user.application.service;

import com.sprintforge.identity.user.application.port.in.query.GetAllUsers;
import com.sprintforge.identity.user.application.port.in.query.GetAllUsersQuery;
import com.sprintforge.identity.user.application.port.out.persistence.FindAllUsers;
import com.sprintforge.identity.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetAllUserImpl implements GetAllUsers {

    private final FindAllUsers findAllUsers;

    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return findAllUsers.findAll(query);
    }
}
