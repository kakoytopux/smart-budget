--liquibase formatted sql
--changeset puxichhh:1

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    account_balance DECIMAL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS transactions (
    id SERIAL PRIMARY KEY,
    transaction_sum DECIMAL,
    category VARCHAR(255),
    description VARCHAR(255),
    bank_id BIGINT,
    created_at TIMESTAMP NOT NULL,
    user_id BIGINT,
    cash_back_sum DECIMAL
);
