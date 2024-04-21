package com.panic.panicnotificationservice.services;

import com.panic.panicnotificationservice.models.Client;
import com.panic.panicnotificationservice.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public void createClient(Client client) {
        log.info("createClient:: create new client");
        clientRepository.save(client);
    }

}
