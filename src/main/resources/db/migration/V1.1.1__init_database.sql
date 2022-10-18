CREATE SCHEMA IF NOT EXISTS lab_1;

CREATE TABLE IF NOT EXISTS lab_1.persons (
    id BIGSERIAL PRIMARY KEY,
    name varchar(20) NOT NULL,
    address varchar(128) NOT NULL,
    age SMALLSERIAL NOT NULL,
    work varchar(128) NOT NULL
);