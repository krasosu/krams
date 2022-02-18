package de.krasosu.krams.service;

import de.krasosu.krams.exception.ResourceNotFoundException;
import de.krasosu.krams.model.Person;
import de.krasosu.krams.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person updatePerson(Person person) {
        Optional<Person> personDb = this.personRepository.findById(person.getId());

        if (personDb.isPresent()) {
            Person personUpdate = personDb.get();
            personUpdate.setId(person.getId());
            personUpdate.setName(person.getName());

      /* FIXME
         personUpdate.setDescription(person.getDescription());
      */
            personRepository.save(personUpdate);
            return personUpdate;
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + person.getId());
        }
    }

    @Override
    public List<Person> getAllPerson() {
        return this.personRepository.findAll();
    }

    @Override
    public Person getPersonById(long personId) {

        Optional<Person> personDb = this.personRepository.findById(personId);

        if (personDb.isPresent()) {
            return personDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + personId);
        }
    }

    @Override
    public List<Person> getPersonBySpecification(Specification<Person> spec) {
        return this.personRepository.findAll(spec);
    }

    @Override
    public void deletePerson(long personId) {
        Optional<Person> personDb = this.personRepository.findById(personId);

        if (personDb.isPresent()) {
            this.personRepository.delete(personDb.get());
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + personId);
        }
    }

    @Override
    public List<Person> getPersonByName(String name) {
        return this.personRepository.getPersonByName(name);
    }
}
