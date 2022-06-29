INSERT INTO roles(id, name)
VALUES (1, 'ROLE_CASHIER'), (2, 'ROLE_ADMIN');

INSERT INTO banks(id, name)
VALUES (1, 'Some bank');

INSERT INTO users (username, password, bank_id)
VALUES ('Admin', '$2a$04$.z4Wzip1.NiUJdm0O9DrteVps7rHa04G7xnnKagVqXzsSplV8mbU2', 1);

INSERT INTO user_role(user_id, role_id)
VALUES (1, 2);