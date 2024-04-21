CREATE TABLE client_person (
    person_id  INTEGER NOT NULL,
    client_id  INTEGER NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client (id),
    FOREIGN KEY (person_id) REFERENCES person (id)
);
