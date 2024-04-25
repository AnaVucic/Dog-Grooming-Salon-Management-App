package com.napredno.doggroom.domain;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Represents a salon in which an Appointment is scheduled.
 * Salon is identified with salonID.
 * A salon must have an address as String.
 * A salon must have a City its in, of type City.
 * The Salon class is annotated with @Entity, indicating that it is a JPA entity.
 *
 * @author Ana Vucic
 * @since 0.1.0
 */
@Entity
public class Salon {

    /**
     * Salon's ID as a Long.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salon_id")
    private Long salonID;

    /**
     * Salon's address as String.
     */
    private String address;

    /**
     * Salon's city.
     * A salon must have exactly one City.
     */
    @ManyToOne
    @JoinColumn(name = "zip_code")
    private City city;

    /**
     *  Non-parameterized constructor for class Salon.
     */
    public Salon() {
    }

    /**
     * Parameterized constructor for class Salon.
     * @param salonID Salon's ID as Long
     * @param address Salon's address as String
     * @param city Salon's city of type City
     */
    public Salon(Long salonID, String address, City city) {
        this.salonID = salonID;
        this.address = address;
        this.city = city;
    }

    /**
     * Parameterized constructor for class Salon.
     * @param address Salon's address as String
     * @param city Salon's city of type City
     */
    public Salon(String address, City city) {
        this.address = address;
        this.city = city;
    }

    /**
     * Returns salon's ID.
     * @return Salon's ID as Long.
     */
    public Long getSalonID() {
        return salonID;
    }

    /**
     * Sets salon's ID.
     * @param salonID Salon's ID as Long
     */
    public void setSalonID(Long salonID) {
        if (salonID == null)
            throw new NullPointerException("ID must not be null!");
        this.salonID = salonID;
    }

    /**
     * Returns salon's address.
     * @return Salon's address as String.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets salon's ID.
     * @param address Salon's address as String
     */
    public void setAddress(String address) {
        if (address == null)
            throw new NullPointerException("Address must not be null!");
        if (address.isEmpty())
            throw new IllegalArgumentException("Address must not be empty!");
        if (address.length() < 5 || address.length() > 50 )
            throw new IllegalArgumentException("Address must be between 5 and 50 characters!");
        this.address = address;
    }

    /**
     * Returns salon's City.
     * @return Salon's city of type City.
     */
    public City getCity() {
        return city;
    }

    /**
     * Sets salon's city.
     * @param city Salon's city of type City
     */
    public void setCity(City city) {
        if (city == null)
            throw new NullPointerException("City must not be null!");
        this.city = city;
    }

    @Override
    public String toString() {
        return "Salon{" +
                "salonID=" + salonID +
                ", address='" + address + '\'' +
                ", city=" + city +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salon salon = (Salon) o;
        return salonID.equals(salon.salonID) && address.equals(salon.address) && city.equals(salon.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salonID, address, city);
    }
}
