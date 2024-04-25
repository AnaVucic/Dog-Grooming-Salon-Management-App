package com.napredno.doggroom.service;

import com.napredno.doggroom.domain.Person;
import com.napredno.doggroom.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Person service used for retrieving, adding, changing and deleting persons.
 *
 * @author Ana Vucic
 * @since 0.1.0
 */
@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Returns a list of all persons in the system.
     * @return All persons in a List of type Person
     */
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    /**
     * Creates a new person. Saves new person in database.
     *
     * @param person person data as Person
     *
     * @return Person data that was saved in database
     *
     * @throws IllegalArgumentException person's contact number already exists in database
     * @throws IllegalArgumentException person's first name is null or less than 2 characters long
     * @throws IllegalArgumentException person's last name is null or less than 2 characters long
     * @throws IllegalArgumentException person's contact number is not in format ### ######[#]
     */
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

    /**
     * Removes a person from database.
     *
     * @param personID ID of the personto be removed
     *
     * @throws IllegalStateException person with given ID cannt be found in database
     */
    public void deletePerson(Long personID) {
        boolean exists = personRepository.existsById(personID);
        if (!exists) {
            throw new IllegalStateException("Person with ID " + personID + " does not exist!");
        }
        personRepository.deleteById(personID);
    }

    /**
     * Updates person's data in database.
     *
     * @param personID person's ID as Long
     * @param firstname person's first name as String
     * @param lastname person's last name as String
     * @param contactNumber person's contact number as String
     *
     * @return Person data that was updated in database
     *
     * @throws IllegalStateException person with  given ID cannot be found in database
     */
    @Transactional
    public Person updatePerson(Long personID, String firstname, String lastname, String contactNumber) {
        Optional<Person> p = personRepository.findById(personID);

        if (p.isEmpty()) {
            throw new IllegalArgumentException("Person with id " + personID + " does not exist.");
        }

        Person person = p.get();

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
