package com.sprintforge.identity.role.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;
import java.util.UUID;

public record UpdateRoleRequestDTO(

        @NotBlank(message = "El nombre del rol es obligatorio.")
        @Size(max = 100, message = "El nombre del rol no puede exceder los 100 caracteres.")
        String name,

        @Size(max = 250, message = "La descripción del rol no puede exceder los 250 caracteres.")
        String description,

        @NotEmpty(message = "Debe asignar al menos un permiso al rol.")
        Set<UUID> permissionIds
) {
}
