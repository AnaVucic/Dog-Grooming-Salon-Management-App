package com.napredno.doggroom.domain;

import jakarta.persistence.*;

import java.util.Objects;

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
        if (breedID == null)
            throw new NullPointerException("ID must not be null!");
        this.breedID = breedID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null)
            throw new NullPointerException("Name must not be null!");
        if (name.isEmpty())
            throw new IllegalArgumentException("Name must not be empty!");
        if (name.length() < 3 || name.length() > 30 )
            throw new IllegalArgumentException("Name must be between 3 and 30 characters!");
        this.name = name;
    }

    @Override
    public String toString() {
        return "Breed{" +
                "breedID=" + breedID +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Breed breed = (Breed) o;
        return breedID.equals(breed.breedID) && name.equals(breed.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(breedID, name);
    }
}
