package com.sprintforge.identity.user.domain;

import com.sprintforge.common.domain.exception.ValidationException;
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
    private UserEmail email;
    private UserPassword password;
    private UserEmployeeId employeeId;
    private UserRoleId roleId;
    private UserStatus status;
    private Instant lastLoginAt;
    private boolean isDeleted;
    private Instant emailVerifiedAt;
    private boolean mfaEnabled;
    private String mfaSecret;
    private final Instant createdAt;
    private Instant updatedAt;

    public User(
            String username,
            String email,
            UUID employeeId
    ) {
        Instant now = now();

        this.id = new UserId(randomUUID());
        this.username = new Username(username);
        this.email = new UserEmail(email);
        this.employeeId = new UserEmployeeId(employeeId);
        this.roleId = new UserRoleId(UUID.fromString("101afef9-d6ed-40f8-8ac6-4aa5b7e30cd2"));
        this.status = UserStatus.PENDING_ACTIVATION;
        this.isDeleted = false;
        this.emailVerifiedAt = null;
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
            UUID roleId,
            UserStatus status,
            Instant lastLoginAt,
            boolean isDeleted,
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
        this.roleId = new UserRoleId(roleId);
        this.status = status;
        this.lastLoginAt = lastLoginAt;
        this.isDeleted = isDeleted;
        this.emailVerifiedAt = emailVerifiedAt;
        this.mfaEnabled = mfaEnabled;
        this.mfaSecret = mfaSecret;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
        if (this.isDeleted) {
            throw new ValidationException("No se puede desactivar un usuario eliminado");
        }
        if (!this.status.equals(UserStatus.ACTIVE)) {
            throw new ValidationException("El usuario ya está inactivo");
        }
        this.status = UserStatus.DISABLED;
        this.updatedAt = now();
    }

    public void activate() {
        if (this.isDeleted) {
            throw new ValidationException("No se puede activar un usuario eliminado");
        }
        if (this.status.equals(UserStatus.ACTIVE)) {
            throw new ValidationException("El usuario ya está activo");
        }
        this.status = UserStatus.ACTIVE;
        this.updatedAt = now();
    }

    public void delete() {
        if (this.isDeleted) {
            throw new ValidationException("El usuario ya está eliminado");
        }
        this.isDeleted = true;
        this.status = UserStatus.DISABLED;
        this.updatedAt = now();
    }
}
