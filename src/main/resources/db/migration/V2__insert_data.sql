INSERT INTO banks(id, name)
VALUES (1, 'Some bank');

INSERT INTO banks(id, name)
VALUES (2, 'Another bank');

INSERT INTO accounts(id, currency, balance, bank_id)
VALUES (1, 'SOM', 0, 1);

INSERT INTO accounts(id, currency, balance, bank_id)
VALUES (2, 'RUB', 0, 1);

INSERT INTO accounts(id, currency, balance, bank_id)
VALUES (3, 'USD', 0, 1);

INSERT INTO accounts(id, currency, balance, bank_id)
VALUES (4, 'EUR', 0, 1);

INSERT INTO accounts(id, currency, balance, bank_id)
VALUES (5, 'SOM', 0, 2);

INSERT INTO accounts(id, currency, balance, bank_id)
VALUES (6, 'RUB', 0, 2);

INSERT INTO accounts(id, currency, balance, bank_id)
VALUES (7, 'USD', 0, 2);

INSERT INTO accounts(id, currency, balance, bank_id)
VALUES (8, 'EUR', 0, 2);

INSERT INTO roles(id, name)
VALUES (1, 'ROLE_CASHIER'),
       (2, 'ROLE_ADMIN');

INSERT INTO users (username, password, bank_id)
VALUES ('Admin', '$2a$04$.z4Wzip1.NiUJdm0O9DrteVps7rHa04G7xnnKagVqXzsSplV8mbU2', 1);

INSERT INTO user_role(user_id, role_id)
VALUES (1, 2);

INSERT INTO users (username, password, bank_id)
VALUES ('Cashier1', '$2a$04$.z4Wzip1.NiUJdm0O9DrteVps7rHa04G7xnnKagVqXzsSplV8mbU2', 1);

INSERT INTO user_role(user_id, role_id)
VALUES (1, 1);

INSERT INTO users (username, password, bank_id)
VALUES ('Cashier2', '$2a$04$.z4Wzip1.NiUJdm0O9DrteVps7rHa04G7xnnKagVqXzsSplV8mbU2', 2);

INSERT INTO user_role(user_id, role_id)
VALUES (1, 1);