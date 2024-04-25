package com.napredno.doggroom.service;

import com.napredno.doggroom.domain.Breed;
import com.napredno.doggroom.domain.Dog;
import com.napredno.doggroom.domain.Person;
import com.napredno.doggroom.repository.*;
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
class DogServiceTest {

    List<Dog> dogs = new ArrayList<>();

    @Mock
    DogRepository dogRepository;
    @Mock
    PersonRepository personRepository;
    @Mock
    BreedRepository breedRepository;

    @InjectMocks
    DogService dogService;


    @BeforeEach
    void setUp() {
        Person p1 = new Person(1L,"Ana", "Vucic", "060 000000");
        Breed b1 = new Breed(1L,"Westie");
        Dog d1 = new Dog(1L, p1, b1,"Meda");

        Person p2 = new Person(2L,"Dea", "Brkic", "060 111111");
        Breed b2 = new Breed(2L,"English Cocker Spaniel");
        Dog d2 = new Dog(2L, p2, b2,"Malfi");

        dogs.addAll(Arrays.asList(d1, d2));
    }

    @AfterEach
    void tearDown() {
        dogs = new ArrayList<>();
    }

    @Test
    void getAllDogsOk() {
        when(dogRepository.findAll()).thenReturn(dogs);
        List<Dog> returnedDogs = dogService.getAllDogs();
        assertEquals(2, returnedDogs.size());
    }

    @Test
    void addDogOk() {
        Dog dog = dogs.get(0);
        when(personRepository.findById(1L)).thenReturn(Optional.of(dog.getPerson()));
        when(breedRepository.findById(1L)).thenReturn(Optional.of(dog.getBreed()));
        when(dogRepository.save(Mockito.any(Dog.class))).thenReturn(dog);

        Dog addedDog = dogService.addDog("Leo", 1L, 1L);

        assertNotNull(addedDog);
    }

    @Test
    void addDogNoPerson() {
        when(personRepository.findById(100L)).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class,
                () -> dogService.addDog("Leo", 100L, 1L));
    }

    @Test
    void addDogNoBreed() {
        when(breedRepository.findById(100L)).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class,
                () -> dogService.addDog("Leo", 1L, 100L));
    }

    @Test
    void addDogNameNull() {
        assertThrows(IllegalArgumentException.class,
                () -> dogService.addDog(null, 1L, 1L));
    }

    @ParameterizedTest
    @CsvSource({"L", "a"})
    void addDogNameTooShort(String name) {
        assertThrows(IllegalArgumentException.class,
                () -> dogService.addDog(name, 1L, 1L));
    }

    @Test
    void deleteDogOk() {
        Long dogIDToDelete = 1L;
        when(dogRepository.existsById(dogIDToDelete)).thenReturn(true);
        dogService.deleteDog(dogIDToDelete);
    }

    @Test
    void deleteDogNoDog() {
        assertThrows(IllegalStateException.class,
                () -> dogService.deleteDog(100L));
    }
}