CREATE TABLE transactions(
    id BIGSERIAL PRIMARY KEY,
    sender_inn VARCHAR(50),
    receiver_inn VARCHAR(50),
    currency VARCHAR(10),
    transfer_sum NUMERIC(10, 4),
    code VARCHAR(50),
    cashier_id BIGSERIAL REFERENCES users(id),
    sender_bank_id BIGSERIAL REFERENCES banks(id),
    receiver_bank_id BIGSERIAL REFERENCES banks(id),
    status VARCHAR(10) NOT NULL
);

CREATE TABLE accounts(
    id BIGSERIAL PRIMARY KEY,
    currency VARCHAR(10),
    balance NUMERIC(10, 4),
    bank_id BIGSERIAL REFERENCES banks(id)
);

INSERT INTO banks(id, name)
VALUES (2, 'Another bank');

INSERT INTO accounts(id, currency, balance, bank_id)
VALUES (1, 'SOM', 0, 1);

INSERT INTO accounts(id, currency, balance, bank_id)
VALUES (2, 'RUB', 0, 1);

INSERT INTO accounts(id, currency, balance, bank_id)
VALUES (3, 'USD', 0, 1);

INSERT INTO accounts(id, currency, balance, bank_id)
VALUES (4, 'SOM', 0, 2);

INSERT INTO accounts(id, currency, balance, bank_id)
VALUES (5, 'RUB', 0, 2);

INSERT INTO accounts(id, currency, balance, bank_id)
VALUES (6, 'USD', 0, 2);