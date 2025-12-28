package com.sprintforge.identity.role.infrastructure.adapter.in.rest.controller;

import com.sprintforge.identity.role.application.port.result.RoleGeneralReportResult;
import com.sprintforge.identity.role.application.service.GetRoleGeneralReportImpl;
import com.sprintforge.identity.role.infrastructure.adapter.in.rest.dto.RoleGeneralReportResponseDTO;
import com.sprintforge.identity.role.infrastructure.adapter.in.rest.mapper.InternalRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal-api/v1/role")
public class InternalRoleController {

    private final GetRoleGeneralReportImpl getRoleGeneralReport;

    @GetMapping("/general-report")
    public RoleGeneralReportResponseDTO getGeneralReport() {
        RoleGeneralReportResult result = getRoleGeneralReport.handle(null);
        return InternalRestMapper.fromRoleGeneralReportResult(result);
    }
}
