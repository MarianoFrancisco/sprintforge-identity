CREATE TABLE user
(
    id            UUID PRIMARY KEY,

    username      VARCHAR(100) NOT NULL UNIQUE,
    email         VARCHAR(150) NOT NULL UNIQUE,
    password      VARCHAR(255) NOT NULL,

    employee_id   UUID,

    status        VARCHAR(20)  NOT NULL CHECK (
        status IN ('ACTIVE', 'LOCKED', 'DISABLED')
        ),

    last_login_at TIMESTAMP,
    is_deleted    BOOLEAN      NOT NULL DEFAULT FALSE,

    created_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE user_role
(
    user_id UUID NOT NULL,
    role_id UUID NOT NULL,

    PRIMARY KEY (user_id, role_id),

    CONSTRAINT fk_user_role_user
        FOREIGN KEY (user_id) REFERENCES user (id)
            ON DELETE CASCADE,

    CONSTRAINT fk_user_role_role
        FOREIGN KEY (role_id) REFERENCES role (id)
            ON DELETE RESTRICT
);
