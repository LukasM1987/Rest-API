CREATE SEQUENCE hibernate_sequence EXISTS START 1;

CREATE TABLE tasks (
    id serial PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255)
)