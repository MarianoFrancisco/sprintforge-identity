package com.sprintforge.identity.auth.application.service;

import com.sprintforge.identity.auth.application.port.in.command.RefreshToken;
import com.sprintforge.identity.auth.application.port.in.command.RefreshTokenCommand;
import com.sprintforge.identity.auth.application.port.in.result.TokenPairResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class RefreshTokenImpl implements RefreshToken {
    @Override
    public TokenPairResult handle(RefreshTokenCommand command) {
        return null;
    }
}
