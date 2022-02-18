package de.krasosu.krams.repository;

import de.krasosu.krams.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository
        extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {

    @Query(value = "SELECT * FROM PERSON WHERE NAME = :name ", nativeQuery = true)
    List<Person> getPersonByName(@Param("name") String name);

}

