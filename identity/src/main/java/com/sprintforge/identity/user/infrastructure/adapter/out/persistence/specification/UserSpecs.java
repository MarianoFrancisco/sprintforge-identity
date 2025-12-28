package com.sprintforge.identity.user.infrastructure.adapter.out.persistence.specification;

import com.sprintforge.identity.user.domain.valueobject.UserStatus;
import com.sprintforge.identity.user.infrastructure.adapter.out.persistence.entity.UserEntity;
import lombok.experimental.UtilityClass;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

import static java.util.Locale.ROOT;
import static javax.xml.transform.OutputKeys.METHOD;
import static org.apache.commons.lang.StringUtils.isNotBlank;

@NullMarked
@UtilityClass
public class UserSpecs {

    public Specification<UserEntity> searchLike(String searchTerm) {
        String pattern = "%" + searchTerm.toLowerCase() + "%";

        return (root, ignored, cb) ->
                cb.or(
                        cb.like(cb.lower(root.get("username")), pattern),
                        cb.like(cb.lower(root.get("email")), pattern)
                );
    }

    public Specification<UserEntity> withStatus(UserStatus status) {
        return (root, ignored, cb) ->
                cb.equal(root.get(METHOD), status);
    }

    public Specification<UserEntity> withStatus(String status) {
        if (!isNotBlank(status)) {
            return (ignoredRoot, ignoredQuery, cb) -> cb.conjunction();
        }

        UserStatus parsed;
        try {
            parsed = UserStatus.valueOf(status.trim().toUpperCase(ROOT));
        } catch (IllegalArgumentException ex) {
            return (ignoredRoot, ignoredQuery, cb) -> cb.conjunction();
        }

        return withStatus(parsed);
    }

    public Specification<UserEntity> isDeleted(Boolean isDeleted) {
        return (root, ignored, cb) ->
                cb.equal(root.get("isDeleted"), isDeleted);
    }

    public Specification<UserEntity> withFilters(
            @Nullable String searchTerm,
            @Nullable String status,
            @Nullable Boolean isDeleted
    ) {
        Specification<UserEntity> spec =
                (ignoredRoot, ignoredQuery, cb) -> cb.conjunction();

        if (isNotBlank(searchTerm)) {
            spec = spec.and(searchLike(searchTerm));
        }
        if (isNotBlank(status)) {
            spec = spec.and(withStatus(status));
        }
        if (isDeleted != null) {
            spec = spec.and(isDeleted(isDeleted));
        }

        return spec;
    }
}
