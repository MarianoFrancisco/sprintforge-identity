package com.sprintforge.identity.role.infrastructure.adapter.out.persistence.projection;

import java.util.UUID;

public interface RoleUsersReportView {

    UUID getRoleId();

    String getRoleName();

    boolean getRoleActive();

    UUID getUserId();

    String getUsername();

    String getEmail();

    String getUserStatus();
}
