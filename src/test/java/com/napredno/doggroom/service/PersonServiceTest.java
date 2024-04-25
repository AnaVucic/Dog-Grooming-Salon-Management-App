package com.napredno.doggroom.service;

import com.napredno.doggroom.domain.Person;
import com.napredno.doggroom.repository.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    List<Person> persons = new ArrayList<>();

    @Mock
    PersonRepository personRepository;
    @InjectMocks
    PersonService personService;

    @BeforeEach
    void setUp() {
        Person p1 = new Person(1L,"Ana", "Vucic", "060 000000");
        Person p2 = new Person(2L,"Dea", "Brkic", "060 111111");

        persons.addAll(Arrays.asList(p1, p2));
    }

    @AfterEach
    void tearDown() {
        persons = new ArrayList<>();
    }

    @Test
    void getAllPersonsOk() {
        when(personRepository.findAll()).thenReturn(persons);
        List<Person> returnedPersons = personService.getAllPersons();
        assertEquals(2, returnedPersons.size());
    }

    @Test
    void addPersonOk() {
        Person person = persons.get(0);
        when(personRepository.findByContactNumber(Mockito.any(String.class)))
                .thenReturn(Optional.empty());
        when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);

        Person addeddPerson = personService.addPerson(person);

        assertNotNull(addeddPerson);
    }

    @Test
    void addPersonSameContactNumber() {
        Person person = persons.get(0);
        when(personRepository.findByContactNumber(Mockito.any(String.class)))
                .thenReturn(Optional.of(person));
        assertThrows(IllegalArgumentException.class,
                () -> personService.addPerson(person));
    }

    @ParameterizedTest
    @CsvSource({
            "A", "a"
    })
    void addPersonFirstnameTooShort(String firstname) {
        Person person = new Person(1L, firstname, "Vucic", "060 000000");
        assertThrows(IllegalArgumentException.class,
                () -> personService.addPerson(person));
    }

    @ParameterizedTest
    @CsvSource({
            "A", "a"
    })
    void addPersonLastnameTooShort(String lastname) {
        Person person = new Person(1L, "Ana", lastname, "060 000000");
        assertThrows(IllegalArgumentException.class,
                () -> personService.addPerson(person));
    }

    @Test
    void addPersonInvalidContactNumber() {
        Person person = new Person(1L, "Ana", "Vucic", "060123456");
        assertThrows(IllegalArgumentException.class,
                () -> personService.addPerson(person));
    }


    @Test
    void deletePersonOk() {
        Long personIDToDelete = 1L;
        when(personRepository.existsById(personIDToDelete)).thenReturn(true);
        personService.deletePerson(personIDToDelete);
    }

    @Test
    void deleteDogNoPerson() {
        assertThrows(IllegalStateException.class,
                () -> personService.deletePerson(100L));
    }

    @Test
    void updatePersonOk() {
        Person person = persons.get(0);

        when(personRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(person));

        Person updatedPerson =
                personService.updatePerson(
                        1L,
                        "Andrea",
                        "Stojkovic",
                        "060 222222");

        assertNotNull(updatedPerson);
        assertEquals(1L, updatedPerson.getPersonID());
        assertEquals("Andrea", updatedPerson.getFirstname());
        assertEquals("Stojkovic", updatedPerson.getLastname());
        assertEquals("060 222222", updatedPerson.getContactNumber());
    }

    @Test
    void updatePersonNoPerson() {
        when(personRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class,
                () -> personService.updatePerson(
                        1L,
                        "Andrea",
                        "Stojkovic",
                        "060 222222"));
    }
}