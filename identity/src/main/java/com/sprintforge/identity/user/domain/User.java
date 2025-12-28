package com.sprintforge.identity.user.domain;

import com.sprintforge.common.domain.exception.ValidationException;
import com.sprintforge.identity.role.domain.Role;
import com.sprintforge.identity.user.domain.valueobject.*;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

import static java.time.Instant.now;
import static java.util.UUID.randomUUID;

@Getter
public class User {
    private final UserId id;
    private final Username username;
    private final UserEmail email;
    private UserPassword password;
    private final UserEmployeeId employeeId;
    private UserStatus status;
    private Instant lastLoginAt;
    private Instant emailVerifiedAt;
    private boolean mfaEnabled;
    private String mfaSecret;
    private final Instant createdAt;
    private Instant updatedAt;
    private Role role;

    public User(
            String username,
            String email,
            UUID employeeId,
            Role role
    ) {
        Instant now = now();

        this.id = new UserId(randomUUID());
        this.username = new Username(username);
        this.email = new UserEmail(email);
        this.employeeId = new UserEmployeeId(employeeId);
        this.role = validateRole(role);
        this.status = UserStatus.PENDING_ACTIVATION;
        this.mfaEnabled = false;
        this.mfaSecret = null;
        this.createdAt = now;
        this.updatedAt = now;
    }

    public User(
            UUID id,
            String username,
            String email,
            String passwordHash,
            UUID employeeId,
            Role role,
            UserStatus status,
            Instant lastLoginAt,
            Instant emailVerifiedAt,
            boolean mfaEnabled,
            String mfaSecret,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.id = new UserId(id);
        this.username = new Username(username);
        this.email = new UserEmail(email);
        this.password = new UserPassword(passwordHash);
        this.employeeId = new UserEmployeeId(employeeId);
        this.role = validateRole(role);
        this.status = status;
        this.lastLoginAt = lastLoginAt;
        this.emailVerifiedAt = emailVerifiedAt;
        this.mfaEnabled = mfaEnabled;
        this.mfaSecret = mfaSecret;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setPassword(String passwordHash) {
        if (this.status.equals(UserStatus.PENDING_ACTIVATION)) {
            throw new ValidationException("No se puede cambiar la contraseña de un usuario pendiente de activación");
        }
        this.password = new UserPassword(passwordHash);
        this.updatedAt = now();
    }

    public void setInitialPassword(String passwordHash) {
        this.status = UserStatus.ACTIVE;
        this.password = new UserPassword(passwordHash);
        this.updatedAt = now();
    }

    public void markEmailAsVerified() {
        if (this.emailVerifiedAt != null) {
            throw new ValidationException("El correo electrónico ya ha sido verificado");
        }
        this.emailVerifiedAt = now();
        this.updatedAt = now();
    }

    public void updateLastLogin() {
        this.lastLoginAt = now();
        this.updatedAt = now();
    }

    public void changeRole(Role newRole) {
        this.role = validateRole(newRole);
        this.updatedAt = now();
    }

    public void enableMfa(String mfaSecret) {
        if (this.mfaEnabled) {
            throw new ValidationException("Ya se encuentra habilitada la autenticación multifactor");
        }
        this.mfaEnabled = true;
        this.mfaSecret = mfaSecret;
        this.updatedAt = now();
    }

    public void disableMfa() {
        if (!this.mfaEnabled) {
            throw new ValidationException("La autenticación multifactor ya se encuentra deshabilitada");
        }
        this.mfaEnabled = false;
        this.mfaSecret = null;
        this.updatedAt = now();
    }

    public void deactivate() {
        if (this.status.equals(UserStatus.DISABLED)) {
            throw new ValidationException("No se puede desactivar un usuario deshabilitado");
        }
        if (!this.status.equals(UserStatus.ACTIVE)) {
            throw new ValidationException("El usuario ya está inactivo");
        }
        this.status = UserStatus.DISABLED;
        this.updatedAt = now();
    }

    public void activate() {
        if (this.status.equals(UserStatus.DISABLED)) {
            throw new ValidationException("No se puede activar un usuario deshabilitado");
        }
        if (this.status.equals(UserStatus.ACTIVE)) {
            throw new ValidationException("El usuario ya está activo");
        }
        this.status = UserStatus.ACTIVE;
        this.updatedAt = now();
    }

    public void disable() {
        if (this.status.equals(UserStatus.DISABLED)) {
            throw new ValidationException("El usuario ya está deshabilitado");
        }
        this.status = UserStatus.DISABLED;
        this.updatedAt = now();
    }

    private Role validateRole(Role role) {
        if (role == null) {
            throw new ValidationException("El rol no puede estar vacío");
        }
        role.validateActive();
        return role;
    }
}
