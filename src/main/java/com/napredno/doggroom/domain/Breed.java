package com.napredno.doggroom.domain;

import jakarta.persistence.*;

/**
 * Represents a breed that is associated with on object of type Dog.
 * Breed is identified with breedID.
 * A breed must also have a name as String.
 * The Breed class is annotated with @Entity, indicating that it is a JPA entity.
 */
@Entity
public class Breed {

    /**
     * Breed's ID as a Long.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "breed_id")
    private Long breedID;

    /**
     * Breed's name as String.
     */
    private String name;

    /**
     *  Non-parameterized constructor for class Breed.
     */
    public Breed() {
    }

    /**
     * Parameterized constructor for class Breed.
     * @param breedID Breed's ID as Long
     * @param name Breed's name as String
     */
    public Breed(Long breedID, String name) {
        this.breedID = breedID;
        this.name = name;
    }


    /**
     * Parameterized constructor for class Breed.
     * @param name Breed's name as String
     */
    public Breed(String name) {
        this.name = name;
    }

    /**
     * Returns breed's ID.
     * @return Breed's ID as Long.
     */
    public Long getBreedID() {
        return breedID;
    }

    /**
     * Sets breed's ID.
     * @param breedID Breed's ID as Long
     */
    public void setBreedID(Long breedID) {
        this.breedID = breedID;
    }

    /**
     * Returns breed's name.
     * @return Breed's ID as String.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets breed's name.
     * @param name Breed's name as String
     */
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
