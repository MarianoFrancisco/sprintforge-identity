CREATE TABLE mfa_challenge
(
    id         UUID PRIMARY KEY,
    user_id    UUID        NOT NULL,
    code       VARCHAR(20) NOT NULL,

    channel    VARCHAR(20) NOT NULL CHECK (channel IN ('EMAIL', 'SMS', 'APP')),

    expires_at TIMESTAMP   NOT NULL,
    used_at    TIMESTAMP,

    created_at TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_mfa_challenge_user
        FOREIGN KEY (user_id) REFERENCES "user" (id)
            ON DELETE CASCADE
);
