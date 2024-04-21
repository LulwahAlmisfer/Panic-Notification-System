CREATE TABLE client_notification (
    client_id  INTEGER NOT NULL,
    notification_id varchar NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client (id),
    FOREIGN KEY (notification_id) REFERENCES notification (id),
    PRIMARY KEY (notification_id, client_id)
);