package com.sprintforge.identity.user.infrastructure.adapter.out.persistence;

import com.sprintforge.identity.user.application.port.in.query.GetAllUsersQuery;
import com.sprintforge.identity.user.application.port.out.persistence.*;
import com.sprintforge.identity.user.domain.User;
import com.sprintforge.identity.user.infrastructure.adapter.out.persistence.entity.UserEntity;
import com.sprintforge.identity.user.infrastructure.adapter.out.persistence.mapper.UserEntityMapper;
import com.sprintforge.identity.user.infrastructure.adapter.out.persistence.repository.UserJpaRepository;
import com.sprintforge.identity.user.infrastructure.adapter.out.persistence.specification.UserSpecs;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
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
        FindUserByEmail,
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
    public List<User> findAll(GetAllUsersQuery query) {

        Specification<UserEntity> spec = UserSpecs.withFilters(
                query.searchTerm(),
                query.status(),
                query.isDeleted()
        );

        return userJpaRepository.findAll(spec)
                .stream()
                .map(UserEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userJpaRepository.findById(id).map(
                UserEntityMapper::toDomain
        );
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email).map(
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
