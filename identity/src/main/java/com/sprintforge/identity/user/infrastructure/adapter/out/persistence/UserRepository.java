package com.sprintforge.identity.user.infrastructure.adapter.out.persistence;

import com.sprintforge.identity.user.application.port.out.persistence.*;
import com.sprintforge.identity.user.domain.User;
import com.sprintforge.identity.user.infrastructure.adapter.out.persistence.entity.UserEntity;
import com.sprintforge.identity.user.infrastructure.adapter.out.persistence.mapper.UserEntityMapper;
import com.sprintforge.identity.user.infrastructure.adapter.out.persistence.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@NullMarked
@Repository
@RequiredArgsConstructor
public class UserRepository implements
        ExistUserByEmail,
        ExistUserByUsername,
        FindAllUsers,
        FindUserById,
        SaveUser {

    private final UserJpaRepository userJpaRepository;

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userJpaRepository.existsByUsername(username);
    }

    @Override
    public User findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userJpaRepository.findById(id).map(
                UserEntityMapper::toDomain
        );
    }

    @Override
    public User save(User user) {
        UserEntity entity = UserEntityMapper.toEntity(user);
        UserEntity savedEntity = userJpaRepository.save(entity);
        return UserEntityMapper.toDomain(savedEntity);
    }
}
