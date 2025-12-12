CREATE TABLE auth_session
(
    id             UUID PRIMARY KEY,
    user_id        UUID         NOT NULL,
    refresh_token  VARCHAR(255) NOT NULL UNIQUE,
    user_agent     VARCHAR(255),
    ip_address     VARCHAR(50),

    created_at     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    expires_at     TIMESTAMP    NOT NULL,
    revoked_at     TIMESTAMP,
    revoked_reason VARCHAR(255),

    CONSTRAINT fk_auth_session_user
        FOREIGN KEY (user_id) REFERENCES "user" (id)
            ON DELETE CASCADE
);
