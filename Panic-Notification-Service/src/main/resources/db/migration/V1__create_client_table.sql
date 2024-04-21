CREATE TABLE client (
    id                     serial PRIMARY KEY,
    name                   varchar NOT NULL,
    url                    varchar NOT NULL,
    method                 varchar NOT NULL,
    authorization_header   varchar
);