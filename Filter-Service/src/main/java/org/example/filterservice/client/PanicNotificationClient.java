package org.example.filterservice.client;

import lombok.extern.slf4j.Slf4j;
import org.example.filterservice.client.models.Client;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON;


@Service
@Slf4j
public class PanicNotificationClient {

    public static final String baseURL = "http://localhost:8080";
    public static final String clientURL = "/v1/client";

    RestClient restClient = RestClient.create();

    public Set<Client> getClient(Integer personId) {

        Set<Client> clients = restClient.get()
                .uri(baseURL + clientURL + "/clients" + "?personId=" + personId)
                .accept(APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<Set<Client>>() {});
        log.info("getClient:: set of clients {} for personId {}", clients, personId);

        return clients;
    }

}
