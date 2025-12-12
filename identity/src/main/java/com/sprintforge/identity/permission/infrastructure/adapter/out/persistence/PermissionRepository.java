package com.sprintforge.identity.permission.infrastructure.adapter.out.persistence;

import com.sprintforge.identity.permission.application.port.in.query.GetAllPermissionsQuery;
import com.sprintforge.identity.permission.application.port.out.persistence.CountPermissionsById;
import com.sprintforge.identity.permission.application.port.out.persistence.FindAllPermissions;
import com.sprintforge.identity.permission.application.port.out.persistence.FindPermissionByCode;
import com.sprintforge.identity.permission.application.port.out.persistence.FindPermissionById;
import com.sprintforge.identity.permission.domain.Permission;
import com.sprintforge.identity.permission.infrastructure.adapter.out.persistence.entity.PermissionEntity;
import com.sprintforge.identity.permission.infrastructure.adapter.out.persistence.mapper.PermissionEntityMapper;
import com.sprintforge.identity.permission.infrastructure.adapter.out.persistence.repository.PermissionJpaRepository;
import com.sprintforge.identity.permission.infrastructure.adapter.out.persistence.specification.PermissionSpecs;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@NullMarked
@Repository
@RequiredArgsConstructor
public class PermissionRepository implements
        FindAllPermissions,
        FindPermissionByCode,
        FindPermissionById,
        CountPermissionsById {

    private final PermissionJpaRepository permissionJpaRepository;

    @Override
    public List<Permission> findAll(GetAllPermissionsQuery query) {
        Specification<PermissionEntity> spec = PermissionSpecs.withFilters(
                query.searchTerm(),
                query.ids()
        );
        return permissionJpaRepository.findAll(spec)
                .stream()
                .map(PermissionEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Permission> findByCode(String code) {
        return permissionJpaRepository.findByCode(code).map(
                PermissionEntityMapper::toDomain
        );
    }

    @Override
    public Optional<Permission> findById(UUID id) {
        return permissionJpaRepository.findById(id).map(
                PermissionEntityMapper::toDomain
        );
    }

    @Override
    public long countByIdIn(Set<UUID> ids) {
        return permissionJpaRepository.countByIdIn(ids);
    }
}
