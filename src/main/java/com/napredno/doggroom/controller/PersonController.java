package com.napredno.doggroom.controller;

import com.napredno.doggroom.domain.Person;
import com.napredno.doggroom.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/persons")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("")
    public List<Person> getAllPersons(){ return personService.getAllPersons(); }

    @PostMapping
    public Person addPerson(@RequestBody Person person) {
        return personService.addPerson(person);
    }

    @DeleteMapping(path = "{personID}")
    public void deletePerson(@PathVariable("personID") Long personID){
        personService.deletePerson(personID);
    }

    @PutMapping(path = "{personID}")
    public Person updatePerson(
            @PathVariable("personID") Long personID,
            @RequestParam(required = false) String firstname,
            @RequestParam(required = false) String lastname,
            @RequestParam(required = false) String contactNumber)
    {
            return personService.updatePerson(personID, firstname, lastname, contactNumber);
    }
}
