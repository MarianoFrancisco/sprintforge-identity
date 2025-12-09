CREATE TABLE permission
(
    id          UUID PRIMARY KEY,
    code        VARCHAR(100) NOT NULL UNIQUE,
    name        VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    category    VARCHAR(50) NOT NULL
);
