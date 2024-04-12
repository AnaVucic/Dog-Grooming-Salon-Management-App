package com.napredno.doggroom.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

/**
 * Represents a person who can own one or more dogs.
 * Person is identified with personID. Person has their firstname, lastname, contact number.
 * Person's appointment number is determined by the number of appointments they scheduled in the system.
 * One person can have many dogs.
 * Person's contact number must be unique in the database.
 * The Person class is annotated with @Entity, indicating that it is a JPA entity.
 *
 * @author Ana Vucic
 * @since 0.1.0
 *
 */
@Entity
public class Person {

    /**
     * Person's ID as a Long.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long personID;

    /**
     * Person's firstname as a String.
     */
    private String firstname;

    /**
     * Person's lastname as a String.
     */
    private String lastname;

    /**
     * Person's contact number as a String. Contact number is unique to a person.
     * Contact number is in format '### ######[#]'.
     */
    private String contactNumber;

    /**
     * Number of appointments that a person has scheduled as int.
     * Default value is 0.
     */
    private int appointmentNumber;

    /**
     * Set of dogs owned by a person.
     * A person can own many dogs.
     */
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Dog> dogs;

    /**
     *  Non-parameterized constructor for class Person.
     */
    public Person() {
    }

    /**
     * Parameterized constructor for class Person.
     * @param personID Person's ID as String.
     * @param firstname Person's first name as String.
     * @param lastname Person's last name as String.
     * @param contactNumber Person's contact number as String.
     */
    public Person(Long personID, String firstname, String lastname, String contactNumber) {
        this.personID = personID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.contactNumber = contactNumber;
        this.appointmentNumber = 0;
    }

    /**
     * Parameterized constructor for class Person.
     * @param firstname Person's first name as String.
     * @param lastname Person's last name as String.
     * @param contactNumber Person's contact number as String.
     */
    public Person(String firstname, String lastname, String contactNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.contactNumber = contactNumber;
    }

    /**
     * Returns person's ID.
     * @return Person's ID as Long.
     */
    public Long getPersonID() {
        return personID;
    }

    /**
     * Sets person's ID.
     * @param personID Person's ID
     */
    public void setPersonID(Long personID) {
        this.personID = personID;
    }

    /**
     * Returns person's firstname.
     * @return Person's first name as String.
     */
    public String getFirstname() { return firstname; }

    /**
     * Sets person's first name.
     * @param firstname Person's first name
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Returns person's lastname.
     * @return Person's last name as String.
     */
    public String getLastname() { return lastname; }

    /**
     * Sets person's first name.
     * @param lastname Person's last name
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Returns person's contact number.
     * @return Person's contact number as String.
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * Sets person's contact number.
     * @param contactNumber Person's contact number
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * Returns the number of appointments a person has scheduled.
     * @return Number of scheduled appointments as int.
     */
    public int getAppointmentNumber() {
        return appointmentNumber;
    }

    /**
     * Sets the number of appointments a person has scheduled.
     * @param appointmentNumber Number of scheduled appointments
     */
    public void setAppointmentNumber(int appointmentNumber) {
        this.appointmentNumber = appointmentNumber;
    }

    /**
     * Returns dogs owned by a person as a set.
     * @return Set of dogs owned by a person.
     */
    public Set<Dog> getDogs() {
        return dogs;
    }

    /**
     * Sets a set of dogs owned by a person.
     * @param dogs Set of dogs owned by a person
     */
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
