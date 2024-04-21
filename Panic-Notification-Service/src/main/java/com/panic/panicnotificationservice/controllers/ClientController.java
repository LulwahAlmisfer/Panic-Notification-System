package com.panic.panicnotificationservice.controllers;

import com.panic.panicnotificationservice.models.Client;
import com.panic.panicnotificationservice.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/client")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity createClient(@RequestBody Client client) {
        clientService.createClient(client);
        return ResponseEntity.ok().build();
    }

}
