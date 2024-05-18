--liquibase formatted sql
--changeset gridyushkof:2

ALTER TABLE transactions
    ADD CONSTRAINT fk_transactions_user_id
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE;