--liquibase formatted sql
--changeset puxichhh:1

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password TEXT NOT NULL,
    accountBalance INT
);

CREATE TABLE users (
   id SERIAL PRIMARY KEY,
   username VARCHAR(50) UNIQUE NOT NULL,
   password TEXT NOT NULL,
   accountBalance INT
);


