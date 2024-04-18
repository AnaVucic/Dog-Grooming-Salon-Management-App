package com.napredno.doggroom.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dog_id")
    private Long dogID;
    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonBackReference
    private Person person;
    @ManyToOne
    @JoinColumn(name = "breed_id")
    private Breed breed;
    private String name;

    public Dog() {
    }

    public Dog(Long dogID, Person person, Breed breed, String name) {
        this.dogID = dogID;
        this.person = person;
        this.breed = breed;
        this.name = name;
    }

    public Dog(Person person, Breed breed, String name) {
        this.person = person;
        this.breed = breed;
        this.name = name;
    }

    public Long getDogID() {
        return dogID;
    }

    public void setDogID(Long dogID) {
        if (dogID == null)
            throw new NullPointerException("ID must not be null!");
        this.dogID = dogID;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        if (person == null)
            throw new NullPointerException("Person must not be null!");
        this.person = person;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        if (breed == null)
            throw new NullPointerException("Breed must not be null!");
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

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
