CREATE TABLE users
(
    id            BIGINT       NOT NULL AUTO_INCREMENT,
    user_id       VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    name          VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_users_user_id (user_id),
    UNIQUE KEY uk_users_email (email)
);
