
--DROP TABLES IF THEY ALREADY EXIST
DROP TABLE IF EXISTS payments;
DROP TABLE IF EXISTS invoices;
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS clients;

-- CLIENTS TABLE
CREATE TABLE clients (
    client_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    joined_date DATE
);

INSERT INTO clients (name, email, joined_date) VALUES
('Alice Johnson', 'alice@example.com', '2021-01-15'),
('Bob Smith', 'bob.smith@example.com', '2022-06-10'),
('Charlie Chen', 'charlie@example.com', '2023-03-20');

-- ACCOUNTS TABLE
CREATE TABLE accounts (
    account_id SERIAL PRIMARY KEY,
    client_id INTEGER REFERENCES clients(client_id),
    account_type VARCHAR(50),
    balance NUMERIC(12, 2)
);

INSERT INTO accounts (client_id, account_type, balance) VALUES
(1, 'Checking', 10500.75),
(1, 'Savings', 22000.00),
(2, 'Investment', 50000.00),
(3, 'Checking', 3500.00);

-- TRANSACTIONS TABLE
CREATE TABLE transactions (
    transaction_id SERIAL PRIMARY KEY,
    account_id INTEGER REFERENCES accounts(account_id),
    transaction_date DATE,
    amount NUMERIC(10,2),
    type VARCHAR(10) CHECK (type IN ('debit', 'credit')),
    description TEXT
);

INSERT INTO transactions (account_id, transaction_date, amount, type, description) VALUES
(1, '2024-12-01', 120.00, 'debit', 'ATM Withdrawal'),
(1, '2025-01-10', 500.00, 'credit', 'Paycheck'),
(2, '2025-01-01', 2000.00, 'debit', 'Vacation Booking'),
(3, '2025-03-12', 10000.00, 'credit', 'Stock Sale');

-- INVOICES TABLE
CREATE TABLE invoices (
    invoice_id SERIAL PRIMARY KEY,
    client_id INTEGER REFERENCES clients(client_id),
    invoice_date DATE,
    amount NUMERIC(10,2),
    status VARCHAR(20)
);

INSERT INTO invoices (client_id, invoice_date, amount, status) VALUES
(1, '2025-01-01', 2500.00, 'unpaid'),
(1, '2025-01-15', 500.00, 'paid'),
(2, '2025-02-10', 1250.00, 'paid');

-- PAYMENTS TABLE
CREATE TABLE payments (
    payment_id SERIAL PRIMARY KEY,
    invoice_id INTEGER REFERENCES invoices(invoice_id),
    payment_date DATE,
    amount NUMERIC(10,2),
    method VARCHAR(50)
);

INSERT INTO payments (invoice_id, payment_date, amount, method) VALUES
(2, '2025-01-16', 500.00, 'Credit Card'),
(3, '2025-02-11', 1250.00, 'Bank Transfer');