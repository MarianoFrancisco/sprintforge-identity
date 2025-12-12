CREATE TABLE "user"
(
    id                UUID PRIMARY KEY,

    username          VARCHAR(100) NOT NULL UNIQUE,
    email             VARCHAR(150) NOT NULL UNIQUE,
    password          VARCHAR(255),

    employee_id       UUID         NOT NULL UNIQUE,

    role_id           UUID         NOT NULL,

    status            VARCHAR(20)  NOT NULL CHECK (
        status IN ('ACTIVE', 'DISABLED', 'PENDING_ACTIVATION')
        ),

    last_login_at     TIMESTAMP,
    is_deleted        BOOLEAN      NOT NULL DEFAULT FALSE,

    email_verified_at TIMESTAMP,

    mfa_enabled       BOOLEAN      NOT NULL DEFAULT FALSE,
    mfa_secret        VARCHAR(255),

    created_at        TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at        TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_user_role
        FOREIGN KEY (role_id) REFERENCES role (id)
            ON DELETE RESTRICT
);
