package com.napredno.doggroom.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Objects;
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
        if (firstname == null)
            throw new NullPointerException("Firstname must not be null!");
        if (firstname.isEmpty())
            throw new IllegalArgumentException("Firstname must not be empty!");
        if (firstname.length() < 2 || firstname.length() > 50 )
            throw new IllegalArgumentException("Firstname must be between 2 and 50 characters!");
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        if (lastname == null)
            throw new NullPointerException("Lastname must not be null!");
        if (lastname.isEmpty())
            throw new IllegalArgumentException("Lastname must not be empty!");
        if (lastname.length() < 2 || lastname.length() > 50 )
            throw new IllegalArgumentException("Lastname must be between 2 and 50 characters!");
        this.lastname = lastname;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        if (contactNumber == null)
            throw new NullPointerException("Contact number must not be null!");
        if (contactNumber.isEmpty())
            throw new IllegalArgumentException("Contact number must not be empty!");
        if (!contactNumber.matches("\\d{3}\\s\\d{6,7}"))
            throw new IllegalArgumentException("Contact number must be in format ### ######[#]!");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return contactNumber.equals(person.contactNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactNumber);
    }
}
