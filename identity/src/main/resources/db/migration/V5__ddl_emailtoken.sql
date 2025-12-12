CREATE TABLE email_token
(
    id         UUID PRIMARY KEY,
    user_id    UUID         NOT NULL,
    token      VARCHAR(255) NOT NULL UNIQUE,

    purpose    VARCHAR(50)  NOT NULL CHECK (
        purpose IN ('VERIFY_EMAIL', 'SET_INITIAL_PASSWORD', 'RESET_PASSWORD')
        ),

    expires_at TIMESTAMP    NOT NULL,
    used_at    TIMESTAMP,

    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_email_token_user
        FOREIGN KEY (user_id) REFERENCES "user" (id)
            ON DELETE CASCADE
);
