package com.sprintforge.identity.role.infrastructure.adapter.in.rest.controller;

import com.sprintforge.identity.role.application.port.in.command.*;
import com.sprintforge.identity.role.application.port.in.query.GetAllRoles;
import com.sprintforge.identity.role.application.port.in.query.GetRoleById;
import com.sprintforge.identity.role.domain.Role;
import com.sprintforge.identity.role.infrastructure.adapter.in.rest.dto.CreateRoleRequestDTO;
import com.sprintforge.identity.role.infrastructure.adapter.in.rest.dto.RoleResponseDTO;
import com.sprintforge.identity.role.infrastructure.adapter.in.rest.dto.UpdateRoleRequestDTO;
import com.sprintforge.identity.role.infrastructure.adapter.in.rest.mapper.RoleRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/role")
public class RoleController {

    private final GetAllRoles getAllRoles;
    private final GetRoleById getRoleById;
    private final CreateRole createRole;
    private final UpdateRoleDetail updateRoleDetail;
    private final ActivateRole activateRole;
    private final DeactivateRole deactivateRole;
    private final DeleteRole deleteRole;

    @GetMapping
    public List<RoleResponseDTO> getAll(
            @RequestParam(required = false) String searchTerm,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(required = false) Boolean isDeleted
    ) {
        List<Role> roles = getAllRoles.handle(
                RoleRestMapper.toQuery(
                        searchTerm,
                        isActive,
                        isDeleted
                )
        );
        return roles.stream()
                .map(RoleRestMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public RoleResponseDTO getById(@PathVariable("id") UUID id) {
        Role role = getRoleById.handle(
                RoleRestMapper.toQuery(
                        id
                )
        );
        return RoleRestMapper.toResponse(role);
    }

    @PostMapping
    @ResponseStatus(code = CREATED)
    public RoleResponseDTO create(
            @RequestBody CreateRoleRequestDTO dto
    ) {
        Role role = createRole.handle(
                RoleRestMapper.toCreateCommand(
                        dto
                )
        );
        return RoleRestMapper.toResponse(role);
    }

    @PatchMapping
    public RoleResponseDTO updateDetails(
            @RequestParam UUID id,
            @RequestBody @Valid UpdateRoleRequestDTO dto
    ) {
        Role updated = updateRoleDetail.handle(
                RoleRestMapper.toUpdateCommand(
                        id,
                        dto
                ));
        return RoleRestMapper.toResponse(updated);
    }

    @PatchMapping("/{id}:activate")
    public RoleResponseDTO activate(@PathVariable UUID id) {
        Role activated = activateRole.handle(
                RoleRestMapper.toActivateCommand(id)
        );
        return RoleRestMapper.toResponse(activated);
    }

    @PatchMapping("/{id}:deactivate")
    public RoleResponseDTO deactivate(@PathVariable UUID id) {
        Role deactivated = deactivateRole.handle(
                RoleRestMapper.toDeactivateCommand(id)
        );
        return RoleRestMapper.toResponse(deactivated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        deleteRole.handle(
                RoleRestMapper.toDeleteCommand(id)
        );
    }
}
