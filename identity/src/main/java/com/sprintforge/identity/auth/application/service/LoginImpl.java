package com.sprintforge.identity.auth.application.service;

import com.sprintforge.identity.auth.application.exception.InvalidCredentialsException;
import com.sprintforge.identity.auth.application.mapper.AuthSessionMapper;
import com.sprintforge.identity.auth.application.mapper.TokenPairResultMapper;
import com.sprintforge.identity.auth.application.policy.TokenPolicy;
import com.sprintforge.identity.auth.application.port.in.command.Login;
import com.sprintforge.identity.auth.application.port.in.command.LoginCommand;
import com.sprintforge.identity.auth.application.port.in.result.TokenPairResult;
import com.sprintforge.identity.auth.application.port.out.persistence.SaveAuthSession;
import com.sprintforge.identity.auth.application.port.out.security.AccessTokenGenerator;
import com.sprintforge.identity.auth.application.port.out.security.PasswordVerifier;
import com.sprintforge.identity.auth.application.port.out.security.RefreshTokenGenerator;
import com.sprintforge.identity.auth.domain.AuthSession;
import com.sprintforge.identity.user.application.port.in.query.GetUserAuthDataByEmail;
import com.sprintforge.identity.user.application.port.in.query.GetUserAuthDataByEmailQuery;
import com.sprintforge.identity.user.application.port.in.result.UserAuthDataResult;
import com.sprintforge.identity.user.domain.valueobject.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class LoginImpl implements Login {

    private final Clock clock;
    private final GetUserAuthDataByEmail getUserByEmail;
    private final PasswordVerifier passwordVerifier;
    private final RefreshTokenGenerator refreshTokenGenerator;
    private final TokenPolicy tokenPolicy;
    private final SaveAuthSession saveAuthSession;
    private final AccessTokenGenerator accessTokenGenerator;

    @Override
    public TokenPairResult handle(LoginCommand command) {
        UserAuthDataResult userAuthData = getAndValidateUser(command);


        String refreshToken = refreshTokenGenerator.generate();

        Instant now = clock.instant();

        AuthSession authSession = AuthSessionMapper.toDomain(
                userAuthData,
                refreshToken,
                command,
                now,
                tokenPolicy.refreshExpiresAt(now)
        );

        saveAuthSession.save(authSession);

        String accessToken = accessTokenGenerator.generate(
                userAuthData.userId(),
                userAuthData.roleId(),
                now,
                tokenPolicy.accessExpiresAt(now),
                userAuthData.employeeId(),
                authSession.getId().value()
        );

        return TokenPairResultMapper.from(
                accessToken,
                tokenPolicy.accessExpiresInSeconds(),
                refreshToken,
                tokenPolicy.refreshExpiresInSeconds(),
                authSession.getId().value()
        );
    }

    private UserAuthDataResult getAndValidateUser(LoginCommand command) {
        UserAuthDataResult userAuthData = getUserByEmail.handle(
                new GetUserAuthDataByEmailQuery(command.email())
        ).orElseThrow(
                InvalidCredentialsException::new
        );
        if (
                !passwordVerifier.matches(command.password(), userAuthData.password())
                        || !UserStatus.ACTIVE.name().equals(userAuthData.status())
        ) {
            throw new InvalidCredentialsException();
        }
        return userAuthData;
    }
}
