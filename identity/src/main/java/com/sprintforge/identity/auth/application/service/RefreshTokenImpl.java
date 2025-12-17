package com.sprintforge.identity.auth.application.service;

import com.sprintforge.identity.auth.application.exception.InvalidRefreshTokenException;
import com.sprintforge.identity.auth.application.exception.RefreshTokenExpiredException;
import com.sprintforge.identity.auth.application.mapper.TokenPairResultMapper;
import com.sprintforge.identity.auth.application.policy.TokenPolicy;
import com.sprintforge.identity.auth.application.port.in.command.RefreshToken;
import com.sprintforge.identity.auth.application.port.in.command.RefreshTokenCommand;
import com.sprintforge.identity.auth.application.port.in.result.TokenPairResult;
import com.sprintforge.identity.auth.application.port.out.persistence.FindAuthSessionById;
import com.sprintforge.identity.auth.application.port.out.persistence.SaveAuthSession;
import com.sprintforge.identity.auth.application.port.out.security.AccessTokenGenerator;
import com.sprintforge.identity.auth.application.port.out.security.RefreshTokenGenerator;
import com.sprintforge.identity.auth.domain.AuthSession;
import com.sprintforge.identity.user.application.port.in.query.GetUserAuthDataById;
import com.sprintforge.identity.user.application.port.in.query.GetUserAuthDataByIdQuery;
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
public class RefreshTokenImpl implements RefreshToken {

    private final Clock clock;
    private final TokenPolicy tokenPolicy;
    private final FindAuthSessionById findAuthSessionById;
    private final SaveAuthSession saveAuthSession;

    private final RefreshTokenGenerator refreshTokenGenerator;
    private final AccessTokenGenerator accessTokenGenerator;

    private final GetUserAuthDataById getUserAuthDataById;

    @Override
    public TokenPairResult handle(RefreshTokenCommand command) {
        Instant now = clock.instant();

        AuthSession authSession = findAuthSessionById.findById(command.authSessionId())
                .orElseThrow(
                        InvalidRefreshTokenException::new
                );

        this.validateRefreshToken(
                authSession,
                command.refreshToken(),
                now
        );

        UserAuthDataResult userAuthData = this.getAndValidateUser(authSession);

        String newRefreshToken = refreshTokenGenerator.generate();
        Instant newRefreshExpiresAt = tokenPolicy.refreshExpiresAt(now);

        authSession.rotateRefreshToken(newRefreshToken, newRefreshExpiresAt, now);

        saveAuthSession.save(authSession);

        Instant accessExpiresAt = tokenPolicy.accessExpiresAt(now);

        String accessToken = accessTokenGenerator.generate(
                userAuthData.userId(),
                userAuthData.roleId(),
                now,
                accessExpiresAt,
                userAuthData.employeeId(),
                authSession.getId().value()
        );

        return TokenPairResultMapper.from(
                accessToken,
                tokenPolicy.accessExpiresInSeconds(),
                newRefreshToken,
                tokenPolicy.refreshExpiresInSeconds(),
                authSession.getId().value()
        );
    }

    private void validateRefreshToken(
            AuthSession session,
            String refreshToken,
            Instant now
    ) {
        if (!session.compareRefreshToken(refreshToken)) {
            throw new InvalidRefreshTokenException();
        }

        if (session.validateRefreshTokenExpiration(now)) {
            throw new RefreshTokenExpiredException();
        }
    }

    private UserAuthDataResult getAndValidateUser(AuthSession session) {
        UserAuthDataResult userAuthData = getUserAuthDataById.handle(
                new GetUserAuthDataByIdQuery(session.getUserId().value())
        ).orElseThrow(InvalidRefreshTokenException::new);

        if (!UserStatus.ACTIVE.name().equals(userAuthData.status())) {
            throw new InvalidRefreshTokenException();
        }
        return userAuthData;
    }
}
