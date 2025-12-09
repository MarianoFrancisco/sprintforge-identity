package com.sprintforge.identity.role.infrastructure.adapter.out.persistence;

import com.sprintforge.identity.role.application.port.in.query.GetAllRolesQuery;
import com.sprintforge.identity.role.application.port.out.persistence.ExistRoleByName;
import com.sprintforge.identity.role.application.port.out.persistence.ExistsRoleByNameAndIdNot;
import com.sprintforge.identity.role.application.port.out.persistence.FindAllRoles;
import com.sprintforge.identity.role.application.port.out.persistence.FindRoleById;
import com.sprintforge.identity.role.application.port.out.persistence.SaveRole;
import com.sprintforge.identity.role.domain.Role;
import com.sprintforge.identity.role.infrastructure.adapter.out.persistence.entity.RoleEntity;
import com.sprintforge.identity.role.infrastructure.adapter.out.persistence.mapper.RoleEntityMapper;
import com.sprintforge.identity.role.infrastructure.adapter.out.persistence.repository.RoleJpaRepository;
import com.sprintforge.identity.role.infrastructure.adapter.out.persistence.specification.RoleSpecs;
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
public class RoleRepository implements
        ExistRoleByName,
        ExistsRoleByNameAndIdNot,
        FindAllRoles,
        FindRoleById,
        SaveRole {

    private final RoleJpaRepository roleJpaRepository;

    @Override
    public boolean existsByName(String name) {
        return roleJpaRepository.existsByName(name);
    }

    @Override
    public boolean existsByNameAndIdNot(String name, UUID id) {
        return roleJpaRepository.existsByNameAndIdNot(name, id);
    }

    @Override
    public List<Role> findAll(GetAllRolesQuery query) {
        Specification<RoleEntity> spec = RoleSpecs.withFilters(
                query.searchTerm(),
                query.isActive(),
                query.isDeleted()
        );

        return roleJpaRepository.findAll(spec)
                .stream()
                .map(RoleEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Role> findById(UUID id) {
        return roleJpaRepository.findById(id).map(
                RoleEntityMapper::toDomain
        );
    }

    @Override
    public Role save(Role role) {
        RoleEntity entity = RoleEntityMapper.toEntity(role);
        RoleEntity savedEntity = roleJpaRepository.save(entity);
        return RoleEntityMapper.toDomain(savedEntity);
    }
}
