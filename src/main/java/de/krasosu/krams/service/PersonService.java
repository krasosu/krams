package de.krasosu.krams.service;

import de.krasosu.krams.model.Person;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface PersonService {

    Person createPerson(Person person);

    Person updatePerson(Person person);

    List<Person> getAllPerson();

    Person getPersonById(long personId);

    List<Person> getPersonBySpecification(Specification<Person> spec);

    void deletePerson(long id);

    List<Person> getPersonByName(String name);
}
