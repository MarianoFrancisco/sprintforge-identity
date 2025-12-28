package com.sprintforge.identity.user.application.service;

import com.sprintforge.identity.user.application.mapper.UserAuthDataResultMapper;
import com.sprintforge.identity.user.application.port.in.query.GetUserAuthDataById;
import com.sprintforge.identity.user.application.port.in.query.GetUserAuthDataByIdQuery;
import com.sprintforge.identity.user.application.port.in.result.UserAuthDataResult;
import com.sprintforge.identity.user.application.port.out.persistence.FindUserById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetUserAuthDataByIdImpl implements GetUserAuthDataById {

    private final FindUserById findUserById;

    @Override
    public Optional<UserAuthDataResult> handle(GetUserAuthDataByIdQuery query) {
        return findUserById.findById(query.id())
                .map(UserAuthDataResultMapper::from);
    }
}
