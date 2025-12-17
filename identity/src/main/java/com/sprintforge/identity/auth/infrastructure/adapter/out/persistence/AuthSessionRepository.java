package com.sprintforge.identity.auth.infrastructure.adapter.out.persistence;

import com.sprintforge.identity.auth.application.port.out.persistence.FindAuthSessionById;
import com.sprintforge.identity.auth.application.port.out.persistence.FindAuthSessionByRefreshToken;
import com.sprintforge.identity.auth.application.port.out.persistence.SaveAuthSession;
import com.sprintforge.identity.auth.domain.AuthSession;
import com.sprintforge.identity.auth.infrastructure.adapter.out.persistence.entity.AuthSessionEntity;
import com.sprintforge.identity.auth.infrastructure.adapter.out.persistence.mapper.AuthSessionEntityMapper;
import com.sprintforge.identity.auth.infrastructure.adapter.out.persistence.repository.AuthSessionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AuthSessionRepository implements
        FindAuthSessionById,
        FindAuthSessionByRefreshToken,
        SaveAuthSession {

    private final AuthSessionJpaRepository authSessionJpaRepository;

    @Override
    public Optional<AuthSession> findById(UUID id) {
        return authSessionJpaRepository.findById(id).map(
                AuthSessionEntityMapper::toDomain
        );
    }

    @Override
    public Optional<AuthSession> findByRefreshToken(String refreshToken) {
        return authSessionJpaRepository.findByRefreshToken(refreshToken).map(
                AuthSessionEntityMapper::toDomain
        );
    }

    @Override
    public AuthSession save(AuthSession authSession) {
        AuthSessionEntity entity = AuthSessionEntityMapper.toEntity(authSession);
        AuthSessionEntity savedEntity = authSessionJpaRepository.save(entity);
        return AuthSessionEntityMapper.toDomain(savedEntity);
    }
}
