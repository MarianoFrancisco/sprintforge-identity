package com.sprintforge.identity.role.infrastructure.adapter.out.persistence.specification;

import com.sprintforge.identity.role.infrastructure.adapter.out.persistence.entity.RoleEntity;
import lombok.experimental.UtilityClass;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

import static org.apache.commons.lang.StringUtils.isNotBlank;

@NullMarked
@UtilityClass
public class RoleSpecs {

    public Specification<RoleEntity> nameOrDescriptionLike(String searchTerm) {
        String pattern = "%" + searchTerm.toLowerCase() + "%";

        return (root, ignored, cb) ->
                cb.or(
                        cb.like(cb.lower(root.get("name")), pattern),
                        cb.like(cb.lower(root.get("description")), pattern)
                );
    }

    public Specification<RoleEntity> isActive(Boolean isActive) {
        return (root, ignored, cb) ->
                cb.equal(root.get("isActive"), isActive);
    }

    public Specification<RoleEntity> isDeleted(Boolean isDeleted) {
        return (root, ignored, cb) ->
                cb.equal(root.get("isDeleted"), isDeleted);
    }

    public Specification<RoleEntity> withFilters(
            @Nullable String searchTerm,
            @Nullable Boolean isActive,
            @Nullable Boolean isDeleted
    ) {
        Specification<RoleEntity> spec = (ignoredRoot, ignoredQuery, cb) -> cb.conjunction();

        if (isNotBlank(searchTerm)) {
            spec = spec.and(nameOrDescriptionLike(searchTerm));
        }
        if (isActive != null) {
            spec = spec.and(isActive(isActive));
        }
        if (isDeleted != null) {
            spec = spec.and(isDeleted(isDeleted));
        }

        return spec;
    }
}
