package com.panic.panicnotificationservice.repositories;

import com.panic.panicnotificationservice.models.Client;
import com.panic.panicnotificationservice.models.ClientNotification;
import org.springframework.data.repository.CrudRepository;

public interface ClientNotificationRepository extends CrudRepository<ClientNotification, Client> {
}