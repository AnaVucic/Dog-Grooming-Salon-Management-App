package com.napredno.doggroom.service;

import com.napredno.doggroom.domain.Breed;
import com.napredno.doggroom.domain.Dog;
import com.napredno.doggroom.domain.Person;
import com.napredno.doggroom.repository.BreedRepository;
import com.napredno.doggroom.repository.DogRepository;
import com.napredno.doggroom.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogService {

    private final DogRepository dogRepository;
    private final PersonRepository personRepository;
    private final BreedRepository breedRepository;

    @Autowired
    public DogService(DogRepository dogRepository, PersonRepository personRepository, BreedRepository breedRepository) {
        this.dogRepository = dogRepository;
        this.personRepository = personRepository;
        this.breedRepository = breedRepository;
    }

    public List<Dog> getAllDogs () {
        return dogRepository.findAll();
    }

    public Dog addDog(String name, Long personID, Long breedID){
        Optional<Person> p = personRepository.findById(personID);
        Optional<Breed> b = breedRepository.findById(breedID);

        if (p.isEmpty()){
            throw new IllegalArgumentException("Person with id " + personID + " does not exist!");
        }
        if (b.isEmpty()){
            throw new IllegalArgumentException("Breed with id " + breedID + " does not exist!");
        }
        if(name == null || name.length() < 2){
            throw new IllegalArgumentException("Dog's name length must be at least two characters!");
        }

        Dog d = new Dog();
        d.setName(name);
        d.setPerson(p.get());
        d.setBreed(b.get());
        System.out.println(d);
        return dogRepository.save(d);
    }

    public void deleteDog(Long dogID) {
        boolean exists = dogRepository.existsById(dogID);
        if (!exists) {
            throw new IllegalStateException("Dog with ID " + dogID + " does not exist");
        }
        dogRepository.deleteById(dogID);
    }
}
