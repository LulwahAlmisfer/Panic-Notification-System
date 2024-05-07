package com.panic.panicnotificationservice.controllers;

import com.panic.panicnotificationservice.models.Client;
import com.panic.panicnotificationservice.models.ClientDto;
import com.panic.panicnotificationservice.models.Person;
import com.panic.panicnotificationservice.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/client")
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.createClient(client));
    }

    @PostMapping("/add-ids")
    public ResponseEntity add_ids(@RequestBody Set<Person> persons, @RequestParam Integer clientId) {
        clientService.addPersons(persons,clientId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/clients")
    public ResponseEntity<Set<Client>> getClients(@RequestParam Integer personId) {
        return ResponseEntity.ok().body(clientService.getClients(personId));
    }

    @GetMapping("/callback-info")
    public ResponseEntity<ClientDto> getCallbackInfo(@RequestParam Integer clientId) {
        return ResponseEntity.ok().body(clientService.getCallbackInfo(clientId));
    }

}
