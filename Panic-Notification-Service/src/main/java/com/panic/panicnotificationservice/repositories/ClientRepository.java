package com.panic.panicnotificationservice.repositories;

import com.panic.panicnotificationservice.models.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {

    boolean existsByApiKey(String apiKey);
}