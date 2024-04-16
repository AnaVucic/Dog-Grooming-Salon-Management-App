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

/**
 * Dog service used for retrieving, adding and deleting dogs.
 *
 * @author Ana Vucic
 * @since 0.1.0
 */
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

    /**
     * Returns a list of all dogs in the system.
     * @return All dogs in a List of type Dog
     */
    public List<Dog> getAllDogs () {
        return dogRepository.findAll();
    }

    /**
     * Creates a new dog. Saves new dog in database.
     *
     * @param name Dog's name as String
     * @param personID Dog's owner ID as Long
     * @param breedID Dog's breed ID as Long
     *
     * @return Dog data that was saved in database
     *
     * @throws IllegalArgumentException person with  given ID cannot be found in database
     * @throws IllegalArgumentException breed with  given ID cannot be found in database
     * @throws IllegalArgumentException dog's name is null or less than 2 characters long
     */
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

    /**
     * Removes a dog from database.
     *
     * @param dogID ID of the dog to be removed
     *
     * @throws IllegalStateException dog with given ID cannot be found in database
     */
    public void deleteDog(Long dogID) {
        boolean exists = dogRepository.existsById(dogID);
        if (!exists) {
            throw new IllegalStateException("Dog with ID " + dogID + " does not exist");
        }
        dogRepository.deleteById(dogID);
    }
}
