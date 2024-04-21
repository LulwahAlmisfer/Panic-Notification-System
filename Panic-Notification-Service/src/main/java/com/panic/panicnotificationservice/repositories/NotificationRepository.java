package com.panic.panicnotificationservice.repositories;

import com.panic.panicnotificationservice.models.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Integer> {
}