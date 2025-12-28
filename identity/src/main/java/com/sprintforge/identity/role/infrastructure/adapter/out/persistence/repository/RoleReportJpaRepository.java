package com.sprintforge.identity.role.infrastructure.adapter.out.persistence.repository;

import com.sprintforge.identity.role.infrastructure.adapter.out.persistence.entity.RoleEntity;
import com.sprintforge.identity.role.infrastructure.adapter.out.persistence.projection.RoleUsersReportView;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

@NullMarked
public interface RoleReportJpaRepository extends JpaRepository<RoleEntity, UUID> {

    @Query(value = """
            SELECT
                r.id         AS roleId,
                r.name       AS roleName,
                r.is_active  AS roleActive,
                u.id         AS userId,
                u.username   AS username,
                u.email      AS email,
                u.status     AS userStatus
            FROM role r
            LEFT JOIN "user" u ON u.role_id = r.id
            WHERE r.is_deleted = false
            ORDER BY r.name, u.username
            """, nativeQuery = true)
    List<RoleUsersReportView> findRoleUsersReport();
}
