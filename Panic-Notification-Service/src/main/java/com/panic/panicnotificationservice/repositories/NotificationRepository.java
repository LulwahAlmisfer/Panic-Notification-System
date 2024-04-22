package com.panic.panicnotificationservice.repositories;

import com.panic.panicnotificationservice.models.Client;
import com.panic.panicnotificationservice.models.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Integer> {

     Set<Notification> findByClients(Client client);
}