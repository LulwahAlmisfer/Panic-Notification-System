package com.panic.panicnotificationservice.repositories;

import com.panic.panicnotificationservice.models.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
}