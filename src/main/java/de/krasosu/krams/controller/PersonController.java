package de.krasosu.krams.controller;

import de.krasosu.krams.model.Person;
import de.krasosu.krams.service.PersonService;
import net.kaczmarzyk.spring.data.jpa.domain.*;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getAllPerson() {
        return ResponseEntity.ok().body(personService.getAllPerson());
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable long id) {
        return ResponseEntity.ok().body(personService.getPersonById(id));
    }

    @RequestMapping(value = "/debug")
    @PostMapping
    public ResponseEntity<List<Person>> debug(
            @Join(path = "skills", alias = "s")
            @Spec(path = "s.type", params = "skilltype", spec = Like.class) Specification<Person> spec) {

        return ResponseEntity.ok().body(this.personService.getPersonBySpecification(spec));

    }

    @GetMapping(value = "/persons/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Person>> getPersonBySpecification(
            @Join(path = "skills", alias = "s")
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "name", params = "name", spec = EqualIgnoreCase.class),
                    @Spec(path = "age", params = "age", spec = Equal.class),
                    @Spec(path = "city", params = "city", spec = Like.class),
                    @Spec(path = "zipCode", params = "zipCode", spec = In.class),
                    @Spec(path = "enabled", params = "enabled", spec = EqualIgnoreCase.class),
                    @Spec(path = "creationDate", params = "creationDate", spec = Equal.class),
                    @Spec(
                            path = "creationDate",
                            params = {"creationDateGt", "creationDateLt"},
                            spec = Between.class),
                    @Spec(path = "s.type", params = "skilltype", spec = Like.class),
                    @Spec(path = "s.name", params = "skillname", spec = Like.class)
            })
                    Specification<Person> spec) {

        return ResponseEntity.ok().body(this.personService.getPersonBySpecification(spec));
    }

    @GetMapping(value = "/persons/lookup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Person>> getPersonByName(@RequestParam("name") String name) {

        return ResponseEntity.ok().body(this.personService.getPersonByName(name));
    }

    @PostMapping("/persons")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        return ResponseEntity.ok().body(this.personService.createPerson(person));
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable long id, @RequestBody Person person) {
        person.setId(id);
        return ResponseEntity.ok().body(this.personService.updatePerson(person));
    }

    @DeleteMapping("/persons/{id}")
    public HttpStatus deletePerson(@PathVariable long id) {
        this.personService.deletePerson(id);
        return HttpStatus.OK;
    }


}
