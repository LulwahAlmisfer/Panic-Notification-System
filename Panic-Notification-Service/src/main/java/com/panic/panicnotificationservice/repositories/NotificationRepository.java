package com.panic.panicnotificationservice.repositories;

import com.panic.panicnotificationservice.models.Notification;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends CrudRepository<Notification, Integer> {
}