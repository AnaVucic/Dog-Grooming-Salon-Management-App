package com.napredno.doggroom.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.Objects;

/**
 * Represents a dog.
 * Dog is identified with dogID.
 * The dog must have its owner of type Person. Dog must have one and only one Person as its owner.
 * The dog must have its breed of type Breed. Dog can have one and only one associated Breed.
 * The dog must have a name as a String.
 * The Dog class is annotated with @Entity, indicating that it is a JPA entity.
 *
 * @author Ana Vucic
 * @since 0.1.0
 *
 */
@Entity
public class Dog {

    /**
     * Dog's ID as a Long.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dog_id")
    private Long dogID;

    /**
     * Dog's owner of type Person.
     * A dog must have exactly one owner.
     */
    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonBackReference
    private Person person;

    /**
     * Dog's breed of type Breed.
     * A dog must have exactly one breed.
     */
    @ManyToOne
    @JoinColumn(name = "breed_id")
    private Breed breed;

    /**
     * Dog's name as a String.
     */
    private String name;

    /**
     *  Non-parameterized constructor for class Dog.
     */
    public Dog() {
    }

    /**
     * Parameterized constructor for class Dog.
     * @param dogID Dog's ID as a String
     * @param person Dog's owner of type Person
     * @param breed Dog's breed of type Breed
     * @param name Dog's name as a String
     */
    public Dog(Long dogID, Person person, Breed breed, String name) {
        this.dogID = dogID;
        this.person = person;
        this.breed = breed;
        this.name = name;
    }

    /**
     * Parameterized constructor for class Dog.
     * @param person Dog's owner of type Person
     * @param breed Dog's breed of type Breed
     * @param name Dog's name as a String
     */
    public Dog(Person person, Breed breed, String name) {
        this.person = person;
        this.breed = breed;
        this.name = name;
    }

    /**
     * Returns dog's ID.
     * @return Dog's ID as Long.
     */
    public Long getDogID() {
        return dogID;
    }

    /**
     * Sets dog's ID.
     * @param dogID Dog's ID as Long
     */
    public void setDogID(Long dogID) {
        if (dogID == null)
            throw new NullPointerException("ID must not be null!");
        this.dogID = dogID;
    }

    /**
     * Returns dog's owner of type Person.
     * @return Dog's owner of type Person.
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Sets dog's owner of type Person.
     * @param person Dog's owner of type Person
     */
    public void setPerson(Person person) {
        if (person == null)
            throw new NullPointerException("Person must not be null!");
        this.person = person;
    }

    /**
     * Returns dog's breed of type Breed.
     * @return Dog's breed of type Breed.
     */
    public Breed getBreed() {
        return breed;
    }

    /**
     * Sets dog's breed of type Breed.
     * @param breed Dog's breed of type Breed
     */
    public void setBreed(Breed breed) {
        if (breed == null)
            throw new NullPointerException("Breed must not be null!");
        this.breed = breed;
    }

    /**
     * Returns dog's name as String.
     * @return Dog's name as String.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets dog's name as String.
     * @param name Dog's name as String
     */
    public void setName(String name) {
        if (name == null)
            throw new NullPointerException("Name must not be null!");
        if (name.isEmpty())
            throw new IllegalArgumentException("Name must not be empty!");
        if (name.length() < 2 || name.length() > 50 )
            throw new IllegalArgumentException("Name must be between 2 and 50 characters!");
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "dogID=" + dogID +
                ", person=" + person +
                ", breed=" + breed +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return dogID.equals(dog.dogID) && name.equals(dog.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dogID, person, breed, name);
    }
}
