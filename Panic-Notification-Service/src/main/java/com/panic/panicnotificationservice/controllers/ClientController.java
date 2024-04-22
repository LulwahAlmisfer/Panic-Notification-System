package com.panic.panicnotificationservice.controllers;

import com.panic.panicnotificationservice.models.Client;
import com.panic.panicnotificationservice.models.Notification;
import com.panic.panicnotificationservice.models.Person;
import com.panic.panicnotificationservice.services.ClientService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;


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

    @PostMapping("/add-ids")
    public ResponseEntity add_ids(@RequestBody Set<Person> persons, @RequestParam Integer clientId) {
        clientService.addPersons(persons,clientId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/clients")
    public ResponseEntity<Set<Client>> test(@RequestParam Integer personId) {
        return ResponseEntity.ok().body(clientService.getClients(personId));
    }

}
