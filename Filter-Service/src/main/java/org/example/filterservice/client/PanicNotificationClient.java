package org.example.filterservice.client;

import lombok.extern.slf4j.Slf4j;
import org.example.filterservice.client.models.Client;
import org.example.filterservice.client.models.Notification;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON;


@Service
@Slf4j
public class PanicNotificationClient {

    public static final String baseURL = "http://localhost:8080";
    public static final String clientURL = "/v1/client";

    RestClient restClient = RestClient.create();

    public Set<Client> getClient(Integer personId) {

        try {
            Set<Client> clients = restClient.get()
                    .uri(baseURL + clientURL + "/clients" + "?personId=" + personId)
                    .accept(APPLICATION_JSON)
                    .retrieve()
                    .body(new ParameterizedTypeReference<Set<Client>>() {
                    });
            log.info("getClient:: set of clients {} for personId: {}", clients, personId);

            return clients;
        } catch (RestClientException e) {
            log.error("No clients found for personId {}: {}", personId, e.getMessage());
            return Collections.emptySet();
        } catch (Exception e) {
            log.error("Service call failed for personId {}: {}", personId, e.getMessage());
            throw new RuntimeException("Service communication error", e);
        }
    }
    public Set<Notification> getNotifications(Integer clientId) {

        try {
            Set<Notification> notifications = restClient.get()
                    .uri(baseURL + "/v1/notification" + "?clientId=" + clientId)
                    .accept(APPLICATION_JSON)
                    .retrieve()
                    .body(new ParameterizedTypeReference<Set<Notification>>() {
                    });
            log.info("getClient:: set of clients {} for clientId: {}", notifications, clientId);

            return notifications;

        } catch (Exception e) {
            log.error("Service call failed for clientId {}: {}", clientId, e.getMessage());
            throw new RuntimeException("Service communication error", e);
        }

    }

}
