package com.panic.panicnotificationservice.repositories;

import com.panic.panicnotificationservice.models.Person;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
    @EntityGraph(attributePaths = {"client"})
     Optional<Person> findPersonById(Integer id);

}