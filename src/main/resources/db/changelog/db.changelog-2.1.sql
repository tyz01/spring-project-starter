--liquibase formatted sql

--changeset jenyanogach:1
CREATE TABLE IF NOT EXISTS revision
(
    id BIGINT PRIMARY KEY ,
    TIMESTAMP BIGINT NOT NULL
);

--changeset jenyanogach:2
CREATE TABLE IF NOT EXISTS users_aud
(
    id BIGINT,
    rev BIGINT REFERENCES revision (id),
    revtype SMALLINT ,
    username VARCHAR(64) NOT NULL UNIQUE ,
    birth_date DATE,
    firstname VARCHAR(64),
    lastname VARCHAR(64),
    role VARCHAR(32),
    company_id BIGINT
);