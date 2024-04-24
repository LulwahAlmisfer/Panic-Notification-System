package com.panic.panicnotificationservice.services;

import com.panic.panicnotificationservice.models.Client;
import com.panic.panicnotificationservice.models.ClientDto;
import com.panic.panicnotificationservice.models.Person;
import com.panic.panicnotificationservice.repositories.ClientRepository;
import com.panic.panicnotificationservice.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



@Service
@Slf4j
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final PersonRepository personRepository;

    public void createClient(Client client) {
        log.info("createClient:: create new client");
        clientRepository.save(client);
    }

    public  void addPersons(Set<Person> personSet, Integer clientId) {
        log.info("addNotification: Add notifications for client id {}", clientId);
        Set<Person>  allPersonSet = new LinkedHashSet<>();
        Optional<Client> optionalClient = clientRepository.findById(clientId);

        if (optionalClient.isEmpty()) {
            throw new NoSuchElementException("Client with ID " + clientId + " not found");
        }
        Client client = optionalClient.get();


        allPersonSet = personSet.stream()
                .filter(p -> !personRepository.findPersonById(p.getId()).isPresent())
                .collect(Collectors.toSet());
         personRepository.saveAll(allPersonSet);

          client.getPerson().addAll(personSet);
          clientRepository.save(client);

    }

   public  Set<Client> getClients(Integer personId){
        Optional<Person> optionalPerson = personRepository.findPersonById(personId);

        if (optionalPerson.isEmpty()) {
            throw new NoSuchElementException("Person with ID " + personId + " not found");
        }

        return optionalPerson.get().getClient();
    }

    public ClientDto getCallbackInfo(Integer clientId){

        Optional<Client> optionalClient = clientRepository.findById(clientId);

        if (optionalClient.isEmpty()) {
            throw new NoSuchElementException("Client with ID " + clientId + " not found");
        }
        Client client = optionalClient.get();

        return ClientDto.builder()
                .name(client.getName())
                .url(client.getUrl())
                .method(client.getMethod())
                .authorizationHeader(client.getAuthorizationHeader())
                .build();
    }
}
