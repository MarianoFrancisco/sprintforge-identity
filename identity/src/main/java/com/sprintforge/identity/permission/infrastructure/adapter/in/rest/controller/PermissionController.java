package com.sprintforge.identity.permission.infrastructure.adapter.in.rest.controller;

import com.sprintforge.identity.permission.application.port.in.query.GetAllPermissions;
import com.sprintforge.identity.permission.application.port.in.query.GetPermissionByCode;
import com.sprintforge.identity.permission.application.port.in.query.GetPermissionById;
import com.sprintforge.identity.permission.domain.Permission;
import com.sprintforge.identity.permission.infrastructure.adapter.in.rest.dto.PermissionResponseDTO;
import com.sprintforge.identity.permission.infrastructure.adapter.in.rest.mapper.PermissionRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/permission")
public class PermissionController {

    private final GetAllPermissions getAllPermissions;
    private final GetPermissionById getPermissionById;
    private final GetPermissionByCode getPermissionByCode;

    @GetMapping
    public List<PermissionResponseDTO> getAll(
            @RequestParam(required = false) String searchTerm
    ) {
        List<Permission> permissions = getAllPermissions.handle(
                PermissionRestMapper.toQuery(
                        searchTerm
                )
        );
        return permissions.stream()
                .map(PermissionRestMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public PermissionResponseDTO getById(
            @RequestParam UUID id
    ) {
        Permission permission = getPermissionById.handle(
                PermissionRestMapper.toQueryById(
                        id
                )
        );
        return PermissionRestMapper.toResponse(permission);
    }

    @GetMapping("/code/{code}")
    public PermissionResponseDTO getByCode(
            @RequestParam String code
    ) {
        Permission permission = getPermissionByCode.handle(
                PermissionRestMapper.toQueryByCode(
                        code
                )
        );
        return PermissionRestMapper.toResponse(permission);
    }
}
