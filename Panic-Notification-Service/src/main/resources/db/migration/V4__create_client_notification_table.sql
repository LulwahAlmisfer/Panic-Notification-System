CREATE TABLE client_notification (
    client_id  INTEGER NOT NULL PRIMARY KEY,
    notification_id INTEGER NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client (id),
    FOREIGN KEY (notification_id) REFERENCES notification (id)
);