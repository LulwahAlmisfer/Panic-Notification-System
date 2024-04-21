package com.panic.panicnotificationservice.services;

import com.panic.panicnotificationservice.models.Client;
import com.panic.panicnotificationservice.models.Notification;
import com.panic.panicnotificationservice.repositories.ClientRepository;
import com.panic.panicnotificationservice.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {

    private final ClientRepository clientRepository;
    private final NotificationRepository notificationRepository;

    public void addNotifications(Set<String> notificationIds, Integer clientId) {
        log.info("addNotification: Add notifications for client id {}", clientId);
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if (!optionalClient.isPresent()) {
            throw new NoSuchElementException("Client with ID " + clientId + " not found");
        }
        Client client = optionalClient.get();

        Set<Notification> notifications = notificationIds.stream()
                .map(id -> notificationRepository.findById(Integer.valueOf(id))
                        .orElseThrow(() -> new NoSuchElementException("Notification with ID " + id + " not found")))
                .collect(Collectors.toSet());

        client.setNotifications(notifications);
        clientRepository.save(client);
    }

}
