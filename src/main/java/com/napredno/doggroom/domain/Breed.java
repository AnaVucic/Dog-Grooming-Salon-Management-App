package com.napredno.doggroom.domain;

import jakarta.persistence.*;

@Entity
public class Breed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "breed_id")
    private Long breedID;
    private String name;

    public Breed() {
    }

    public Breed(Long breedID, String name) {
        this.breedID = breedID;
        this.name = name;
    }

    public Breed(String name) {
        this.name = name;
    }

    public Long getBreedID() {
        return breedID;
    }

    public void setBreedID(Long breedID) {
        this.breedID = breedID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Breed{" +
                "breedID=" + breedID +
                ", name='" + name + '\'' +
                '}';
    }
}
