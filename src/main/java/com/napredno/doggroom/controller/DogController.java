package com.napredno.doggroom.controller;

import com.napredno.doggroom.domain.Dog;
import com.napredno.doggroom.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/dogs")
public class DogController {

    private final DogService dogService;

    @Autowired
    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping("")
    public List<Dog> getAllDogs() {return dogService.getAllDogs();}

    @PostMapping("")
    public Dog addDog(
            @RequestParam String name,
            @RequestParam Long personID,
            @RequestParam Long breedID
    ){
        return dogService.addDog(name, personID, breedID);
    }

    @DeleteMapping(path = "{dogID}")
    public void deleteDog(@PathVariable("dogID") Long dogID){
        dogService.deleteDog(dogID);
    }

}
