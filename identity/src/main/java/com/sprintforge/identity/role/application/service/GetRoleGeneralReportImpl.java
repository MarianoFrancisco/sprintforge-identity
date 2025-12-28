package com.sprintforge.identity.role.application.service;

import com.sprintforge.identity.role.application.port.in.query.GetGeneralRoleReport;
import com.sprintforge.identity.role.application.port.in.query.GetGeneralRoleReportQuery;
import com.sprintforge.identity.role.application.port.out.persistence.LoadRoleGeneralReport;
import com.sprintforge.identity.role.application.port.result.RoleGeneralReportResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetRoleGeneralReportImpl implements GetGeneralRoleReport {

    private final LoadRoleGeneralReport loadRoleGeneralReport;

    @Override
    public RoleGeneralReportResult handle(GetGeneralRoleReportQuery query) {
        return loadRoleGeneralReport.loadRoleGeneralReport();
    }
}
