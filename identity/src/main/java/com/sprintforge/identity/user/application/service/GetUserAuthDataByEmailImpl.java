package com.sprintforge.identity.user.application.service;

import com.sprintforge.identity.user.application.mapper.UserAuthDataResultMapper;
import com.sprintforge.identity.user.application.port.in.query.GetUserAuthDataByEmail;
import com.sprintforge.identity.user.application.port.in.query.GetUserAuthDataByEmailQuery;
import com.sprintforge.identity.user.application.port.in.result.UserAuthDataResult;
import com.sprintforge.identity.user.application.port.out.persistence.FindUserByEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetUserAuthDataByEmailImpl implements GetUserAuthDataByEmail {

    private final FindUserByEmail findUserByEmail;

    @Override
    public Optional<UserAuthDataResult> handle(GetUserAuthDataByEmailQuery query) {
        return findUserByEmail.findByEmail(query.email())
                .map(UserAuthDataResultMapper::from);
    }
}
