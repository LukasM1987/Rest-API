CREATE SEQUENCE hibernate_sequence START 1;

CREATE TABLE tasks IF NOT EXISTS (
    id serial PRIMARY KEY,
    title VARCHAR(255)
    content VARCHAR(255)
)