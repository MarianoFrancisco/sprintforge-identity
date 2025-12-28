package com.sprintforge.identity.role.infrastructure.adapter.out.persistence;

import com.sprintforge.identity.role.application.port.out.persistence.LoadRoleGeneralReport;
import com.sprintforge.identity.role.application.port.result.RoleBlock;
import com.sprintforge.identity.role.application.port.result.RoleGeneralReportResult;
import com.sprintforge.identity.role.application.port.result.RoleUserRow;
import com.sprintforge.identity.role.infrastructure.adapter.out.persistence.projection.RoleUsersReportView;
import com.sprintforge.identity.role.infrastructure.adapter.out.persistence.repository.RoleReportJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class RoleReportRepository implements LoadRoleGeneralReport {

    private final RoleReportJpaRepository roleReportJpaRepository;

    @Override
    public RoleGeneralReportResult loadRoleGeneralReport() {
        List<RoleUsersReportView> rows = roleReportJpaRepository.findRoleUsersReport();

        Map<UUID, Accumulator> grouped = new LinkedHashMap<>();

        for (RoleUsersReportView r : rows) {
            Accumulator acc = grouped.computeIfAbsent(
                    r.getRoleId(),
                    id -> new Accumulator(r.getRoleId(), r.getRoleName(), r.getRoleActive())
            );

            if (r.getUserId() == null) continue;

            RoleUserRow user = new RoleUserRow(
                    r.getUsername(),
                    r.getEmail()
            );

            if ("ACTIVE".equalsIgnoreCase(r.getUserStatus())) {
                acc.activeUsers.add(user);
            } else {
                acc.inactiveUsers.add(user);
            }
        }

        List<RoleBlock> blocks = new ArrayList<>();
        long activeRoles = 0;

        for (Accumulator a : grouped.values()) {
            if (a.roleActive) {
                activeRoles++;
            }

            blocks.add(new RoleBlock(
                    a.roleId,
                    a.roleName,
                    a.roleActive,
                    a.activeUsers.size(),
                    a.inactiveUsers.size(),
                    List.copyOf(a.activeUsers),
                    List.copyOf(a.inactiveUsers)
            ));
        }

        long totalRoles = grouped.size();

        return new RoleGeneralReportResult(
                blocks,
                totalRoles,
                activeRoles
        );
    }

    private static final class Accumulator {
        final UUID roleId;
        final String roleName;
        final boolean roleActive;
        final List<RoleUserRow> activeUsers = new ArrayList<>();
        final List<RoleUserRow> inactiveUsers = new ArrayList<>();

        Accumulator(UUID roleId, String roleName, boolean roleActive) {
            this.roleId = roleId;
            this.roleName = roleName;
            this.roleActive = roleActive;
        }
    }
}
