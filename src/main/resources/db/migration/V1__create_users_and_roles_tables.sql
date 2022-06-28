CREATE TABLE banks(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE roles(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE users(
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(25),
    password VARCHAR(500),
    bank_id BIGSERIAL REFERENCES banks(id)
);

CREATE TABLE user_role(
    user_id BIGSERIAL REFERENCES users(id),
    role_id BIGSERIAL REFERENCES roles(id)
);