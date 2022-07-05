CREATE TABLE banks(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE accounts(
    id BIGSERIAL PRIMARY KEY,
    currency VARCHAR(10),
    balance NUMERIC(10, 4),
    bank_id BIGSERIAL REFERENCES banks(id)
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

CREATE TABLE transactions(
     id BIGSERIAL PRIMARY KEY,
     start_balance NUMERIC(10, 4),
     withdrawal NUMERIC(10, 4),
     deposit NUMERIC(10, 4),
     final_balance NUMERIC(10, 4),
     comment VARCHAR(255) NOT NULL,
     account_id BIGSERIAL REFERENCES accounts(id),
     created_date_time TIMESTAMP
);

CREATE TABLE transfers(
     id BIGSERIAL PRIMARY KEY,
     sender_inn VARCHAR(50),
     receiver_inn VARCHAR(50),
     currency VARCHAR(10),
     sum NUMERIC(10, 4),
     commission NUMERIC(10, 4),
     code VARCHAR(50),
     cashier_id BIGSERIAL REFERENCES users(id),
     sender_bank_id BIGSERIAL REFERENCES banks(id),
     receiver_bank_id BIGSERIAL REFERENCES banks(id),
     status VARCHAR(10) NOT NULL,
     created_date_time TIMESTAMP
);