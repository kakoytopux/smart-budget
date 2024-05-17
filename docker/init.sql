CREATE SCHEMA IF NOT EXISTS smart_budget;
create table  IF NOT EXISTS smart_budget.users
                       id BIGINT PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       account_balance DECIMAL,
                       created_at TIMESTAMP NOT NULL,
                       updated_at TIMESTAMP NOT NULL
);

create table  IF NOT EXISTS smart_budget.transactions (
                              id BIGINT PRIMARY KEY,
                              transaction_sum DECIMAL,
                              category VARCHAR(255),
                              description VARCHAR(255),
                              bank_id BIGINT,
                              created_at TIMESTAMP NOT NULL,
                              user_id BIGINT,
                              cash_back_sum DECIMAL
);