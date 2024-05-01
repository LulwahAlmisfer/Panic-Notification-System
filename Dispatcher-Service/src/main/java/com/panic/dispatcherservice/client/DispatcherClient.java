package com.panic.dispatcherservice.client;

import com.panic.dispatcherservice.models.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@Slf4j
public class DispatcherClient {

    RestClient restClient = RestClient.create();

    public void dispatchCallback(Message m){

        try {
            Object callback = restClient
                    .method(getMethod(m.getClientMethod()))
                    .uri(m.getClientUrl())
                    .accept(APPLICATION_JSON)
                    .body(m)
                    .retrieve();
            
            log.info("dispatchCallback:: message: {}", m);
            log.info("dispatchCallback:: response {}", callback);

        } catch (Exception e) {
            log.error("dispatchCallback call failed for personId {}: {}", m.getPersonId(), e.getMessage());
            throw new RuntimeException("Service communication error", e);
        }

    }

    HttpMethod getMethod(String s){
        return switch (s.toUpperCase()) {
            case "GET" -> HttpMethod.GET;
            case "POST" -> HttpMethod.POST;
            case "PUT" -> HttpMethod.PUT;
            case "DELETE" -> HttpMethod.DELETE;
            case "PATCH" -> HttpMethod.PATCH;
            default -> throw new IllegalArgumentException("Unsupported HTTP method: " + s);
        };
    }
}
