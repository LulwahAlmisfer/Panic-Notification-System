package com.panic.panicnotificationservice.repositories;

import com.panic.panicnotificationservice.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {
}