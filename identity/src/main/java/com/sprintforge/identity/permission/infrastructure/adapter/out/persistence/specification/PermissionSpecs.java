package com.sprintforge.identity.permission.infrastructure.adapter.out.persistence.specification;

import com.sprintforge.identity.permission.infrastructure.adapter.out.persistence.entity.PermissionEntity;
import lombok.experimental.UtilityClass;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

import static org.apache.commons.lang.StringUtils.isNotBlank;

@NullMarked
@UtilityClass
public class PermissionSpecs {

    public Specification<PermissionEntity> nameOrDescriptionLike(String searchTerm) {
        String pattern = "%" + searchTerm.toLowerCase() + "%";

        return (root, ignored, cb) ->
                cb.or(
                        cb.like(cb.lower(root.get("name")), pattern),
                        cb.like(cb.lower(root.get("description")), pattern)
                );
    }

    public Specification<PermissionEntity> withFilters(
            @Nullable String searchTerm
    ) {
        Specification<PermissionEntity> spec =
                (ignoredRoot, ignoredQuery, cb) -> cb.conjunction();

        if (isNotBlank(searchTerm)) {
            spec = spec.and(nameOrDescriptionLike(searchTerm));
        }

        return spec;
    }
}
