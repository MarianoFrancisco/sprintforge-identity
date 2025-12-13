package com.sprintforge.identity.role.domain;

import com.sprintforge.common.domain.exception.ValidationException;
import com.sprintforge.identity.permission.domain.valueobject.PermissionId;
import com.sprintforge.identity.role.domain.valueobject.RoleDescription;
import com.sprintforge.identity.role.domain.valueobject.RoleId;
import com.sprintforge.identity.role.domain.valueobject.RoleName;
import lombok.Getter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static java.time.Instant.now;
import static java.util.UUID.randomUUID;

@Getter
public class Role {

    private final RoleId id;
    private RoleName name;
    private RoleDescription description;

    private final boolean isDefault;
    private boolean isActive;
    private boolean isDeleted;

    private final Instant createdAt;
    private Instant updatedAt;

    private final Set<UUID> permissionIds = new HashSet<>();

    public Role(
            String name,
            String description
    ) {
        this.id = new RoleId(randomUUID());
        this.name = new RoleName(name);
        this.description = new RoleDescription(description);
        this.isDefault = false;
        this.isActive = true;
        this.isDeleted = false;
        Instant now = now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    public Role(
            UUID id,
            String name,
            String description,
            Boolean isDefault,
            boolean isActive,
            boolean isDeleted,
            Instant createdAt,
            Instant updatedAt,
            Set<UUID> permissionIds
    ) {
        this.id = new RoleId(id);
        this.name = new RoleName(name);
        this.description = new RoleDescription(description);
        this.isDefault = isDefault != null ? isDefault : false;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        if (permissionIds != null) {
            this.permissionIds.addAll(permissionIds);
        }
    }

    public void updateDetails(
            String name,
            String description
    ) {
        this.name = new RoleName(name);
        this.description = new RoleDescription(description);
        this.updatedAt = now();
    }

    public void activate() {
        if (this.isDeleted) {
            throw new ValidationException("No se puede activar un rol eliminado");
        }
        if (this.isActive) {
            throw new ValidationException("El rol ya está activo");
        }
        this.isActive = true;
        this.updatedAt = now();
    }

    public void deactivate() {
        if (this.isDeleted) {
            throw new ValidationException("No se puede desactivar un rol eliminado");
        }
        if (!this.isActive) {
            throw new ValidationException("El rol ya está inactivo");
        }
        this.isActive = false;
        this.updatedAt = now();
    }

    public void delete() {
        if (this.isDeleted) {
            throw new ValidationException("El rol ya está eliminado");
        }
        this.isDeleted = true;
        this.updatedAt = now();
    }

    public void setPermissions(Set<UUID> newPermissionIds) {
        if (this.isDeleted) {
            throw new ValidationException("No se puede modificar un rol eliminado");
        }
        if (newPermissionIds == null || newPermissionIds.isEmpty()) {
            throw new ValidationException("Un rol debe tener al menos un permiso");
        }

        this.permissionIds.clear();
        this.permissionIds.addAll(newPermissionIds);
        this.updatedAt = now();
    }

    public Set<UUID> getPermissionIds() {
        return Set.copyOf(permissionIds);
    }
}
