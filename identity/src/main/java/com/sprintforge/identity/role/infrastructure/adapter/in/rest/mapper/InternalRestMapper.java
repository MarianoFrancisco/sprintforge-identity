package com.sprintforge.identity.role.infrastructure.adapter.in.rest.mapper;

import com.sprintforge.identity.role.application.port.result.RoleBlock;
import com.sprintforge.identity.role.application.port.result.RoleGeneralReportResult;
import com.sprintforge.identity.role.application.port.result.RoleUserRow;
import com.sprintforge.identity.role.infrastructure.adapter.in.rest.dto.RoleBlockDTO;
import com.sprintforge.identity.role.infrastructure.adapter.in.rest.dto.RoleGeneralReportResponseDTO;
import com.sprintforge.identity.role.infrastructure.adapter.in.rest.dto.RoleUserRowDTO;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class InternalRestMapper {

    public RoleGeneralReportResponseDTO fromRoleGeneralReportResult(RoleGeneralReportResult result) {
        return new RoleGeneralReportResponseDTO(
                mapRoleBlocks(result.roles()),
                result.totalRoles(),
                result.activeRoles()
        );
    }

    private List<RoleBlockDTO> mapRoleBlocks(List<RoleBlock> blocks) {
        return blocks.stream()
                .map(InternalRestMapper::mapRoleBlock)
                .toList();
    }

    private RoleBlockDTO mapRoleBlock(RoleBlock block) {
        return new RoleBlockDTO(
                block.roleId(),
                block.roleName(),
                block.roleActive(),
                block.activeUsersCount(),
                block.inactiveUsersCount(),
                mapUsers(block.activeUsers()),
                mapUsers(block.inactiveUsers())
        );
    }

    private List<RoleUserRowDTO> mapUsers(List<RoleUserRow> users) {
        return users.stream()
                .map(InternalRestMapper::mapUser)
                .toList();
    }

    private RoleUserRowDTO mapUser(RoleUserRow user) {
        return new RoleUserRowDTO(
                user.cui(),
                user.email()
        );
    }
}
