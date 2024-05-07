package com.napredno.doggroom.domain;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * Represents a breed that is associated with on object of type Dog.
 * Breed is identified with breedID.
 * A breed must also have a name as String.
 * The Breed class is annotated with @Entity, indicating that it is a JPA entity.
 *
 * @author Ana Vucic
 * @since 0.1.0
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
//        this.breedID = breedID;
//        this.name = name;
        setBreedID(breedID);
        setName(name);
    }


    /**
     * Parameterized constructor for class Breed.
     * @param name Breed's name as String
     */
    public Breed(String name) {
//        this.name = name;
        setName(name);
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
     * @throws NullPointerException Breed ID is null
     */
    public void setBreedID(Long breedID) {
        if (breedID == null)
            throw new NullPointerException("ID must not be null!");
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
     * @throws NullPointerException Breed name is null
     * @throws IllegalArgumentException Breed name is an empty String,<br>
     * Breed name is not of length 3-30
     */
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
