package com.sprintforge.identity.role.application.port.in.query;

import com.sprintforge.common.application.port.result.RoleGeneralReportResult;

public interface GetGeneralRoleReport {
    RoleGeneralReportResult handle(GetGeneralRoleReportQuery query);
}
