package com.sprintforge.identity.role.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateRoleRequestDTO(

        @NotBlank(message = "El nombre del rol es obligatorio.")
        @Size(max = 100, message = "El nombre del rol no puede exceder los 100 caracteres.")
        String name,

        @Size(max = 250, message = "La descripción del rol no puede exceder los 250 caracteres.")
        String description,

        @NotNull(message = "Es obligatorio especificar si el rol es por defecto.")
        Boolean isDefault
) {
}
