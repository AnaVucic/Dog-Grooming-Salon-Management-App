package com.napredno.doggroom.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

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
        this.dogID = dogID;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
}
