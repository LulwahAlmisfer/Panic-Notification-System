package com.panic.panicnotificationservice.repositories;

import com.panic.panicnotificationservice.models.ClientPerson;
import com.panic.panicnotificationservice.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface ClientPersonRepository extends CrudRepository<ClientPerson, Person> {
}