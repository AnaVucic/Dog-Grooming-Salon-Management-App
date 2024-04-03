package com.napredno.doggroom.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long personID;
    private String firstname;
    private String lastname;
    private String contactNumber;
    private int appointmentNumber;
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Dog> dogs;

    public Person() {
    }

    public Person(Long personID, String firstname, String lastname, String contactNumber) {
        this.personID = personID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.contactNumber = contactNumber;
        this.appointmentNumber = 0;
    }

    public Person(String firstname, String lastname, String contactNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.contactNumber = contactNumber;
    }

    public Long getPersonID() {
        return personID;
    }

    public void setPersonID(Long personID) {
        this.personID = personID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getAppointmentNumber() {
        return appointmentNumber;
    }

    public void setAppointmentNumber(int appointmentNumber) {
        this.appointmentNumber = appointmentNumber;
    }

    public Set<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(Set<Dog> dogs) {
        this.dogs = dogs;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personID=" + personID +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", appointmentNumber=" + appointmentNumber +
                '}';
    }
}
