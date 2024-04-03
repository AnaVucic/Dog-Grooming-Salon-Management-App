package com.napredno.doggroom.service;

import com.napredno.doggroom.domain.Person;
import com.napredno.doggroom.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person addPerson(Person person) {
        Optional<Person> p = personRepository.findByContactNumber(person.getContactNumber());

        if (p.isPresent()){
            throw new IllegalArgumentException("Person with the same contact number already exists!");
        }
        if(person.getFirstname() == null || person.getFirstname().length() < 2) {
            throw new IllegalArgumentException("Person's firstname length must be at least two characters!");
        }
        if(person.getLastname() == null || person.getLastname().length() < 2) {
            throw new IllegalArgumentException("Person's lastname length must be at least two characters!");
        }
        if(person.getContactNumber() == null || !person.getContactNumber().matches("\\d{3}\\s\\d{6,7}")){
            throw new IllegalArgumentException("Person's contact number must be of format ### ######[#]!");
        }

        return personRepository.save(person);
    }

    public void deletePerson(Long personID) {
        boolean exists = personRepository.existsById(personID);
        if (!exists) {
            throw new IllegalStateException("Person with ID " + personID + " does not exist!");
        }
        personRepository.deleteById(personID);
    }

    @Transactional
    public Person updatePerson(Long personID, String firstname, String lastname, String contactNumber) {
        Person person = personRepository.findById(personID)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Person wiht id " + personID + " does not exist."
                ));
        if (firstname != null && firstname.length() >= 2 && !Objects.equals(person.getFirstname(), firstname)){
            person.setFirstname(firstname);
        }
        if (lastname != null && lastname.length() >= 2 && !Objects.equals(person.getLastname(), lastname)){
            person.setLastname(lastname);
        }
        if (contactNumber != null && contactNumber.length() >= 10 && !Objects.equals(person.getContactNumber(), contactNumber)){
            person.setContactNumber(contactNumber);
        }
        return person;
    }
}
