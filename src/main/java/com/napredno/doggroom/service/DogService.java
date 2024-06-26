package com.napredno.doggroom.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.napredno.doggroom.domain.Breed;
import com.napredno.doggroom.domain.Dog;
import com.napredno.doggroom.domain.Person;
import com.napredno.doggroom.repository.BreedRepository;
import com.napredno.doggroom.repository.DogRepository;
import com.napredno.doggroom.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
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
     * @throws IllegalArgumentException Person with  given ID cannot be found in database,<br>
     * Breed with  given ID cannot be found in database
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

        Dog d = new Dog();
        d.setName(name);
        d.setPerson(p.get());
        d.setBreed(b.get());

        Dog newDog = dogRepository.save(d);

        //-- JSON --//
        String path = "C:\\Users\\ANA\\Desktop\\json\\dogs\\";
        try (PrintWriter out = new PrintWriter(path + "dog_" + newDog.getDogID() + ".json");
             PrintWriter out2 = new PrintWriter(new FileOutputStream(path + "all_dogs.json", true))
        ) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            JsonObject jsonDog = new JsonObject();
            jsonDog.addProperty("id", newDog.getDogID());
            jsonDog.addProperty("name", newDog.getName());
            JsonObject jsonPerson = new JsonObject();
            jsonPerson.addProperty("id", newDog.getPerson().getPersonID());
            jsonPerson.addProperty("firstname", newDog.getPerson().getFirstname());
            jsonPerson.addProperty("lastname", newDog.getPerson().getLastname());
            jsonPerson.addProperty("contact_number", newDog.getPerson().getContactNumber());
            jsonDog.add("person", jsonPerson);
            jsonDog.addProperty("breed", newDog.getBreed().getName());

            out.write(gson.toJson(jsonDog));
            out2.append(gson.toJson(jsonDog));
        } catch (IOException e) { e.printStackTrace(); }

        return newDog;
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

        //-- Deleting the file --//
        String path = "C:\\Users\\ANA\\Desktop\\json\\dogs\\";
        File file = new File(path + "dog_" + dogID + ".json");
        file.delete();

        dogRepository.deleteById(dogID);
    }
}
