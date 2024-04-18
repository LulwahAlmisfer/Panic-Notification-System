package com.panic.panicnotificationservice.repositories;

import com.panic.panicnotificationservice.models.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Integer> {
}