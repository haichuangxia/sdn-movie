package com.neo4j.example.movie.controllers;

import com.neo4j.example.movie.domains.PersonEntity;
import com.neo4j.example.movie.repositories.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public Iterable<PersonEntity> findAllPersons() { return personRepository.findAll(); }

    @GetMapping("/{name}")
    public PersonEntity getPersonByName(@PathVariable String name) {
        return personRepository.getPersonByName(name);
    }

    @GetMapping("/search/{name}")
    public Iterable<PersonEntity> findPersonByNameLike(@PathVariable String name) {
        return personRepository.findPersonByNameLike(name);
    }

    @GetMapping("/actanddirect")
    public List<PersonEntity> getPersonsWhoActAndDirect() {
        return personRepository.getPersonsWhoActAndDirect();
    }
}
