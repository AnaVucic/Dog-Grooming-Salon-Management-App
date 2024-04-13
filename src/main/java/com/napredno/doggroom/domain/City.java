package com.napredno.doggroom.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
/**
 * Represents a city that is associated with on object of type Salon.
 * City is identified with zipCode.
 * City must have its name as String.
 * All salons in one city are represented by a List of type Salon.
 * The City class is annotated with @Entity, indicating that it is a JPA entity.
 */

@Entity
public class City {

    /**
     * City's zip code as String.
     */
    @Id
    // nema @GeneratedValue jer primarni kljuc (ZipCode) ima unapred odredjenu vresnots
    private String zipCode;

    /**
     * City's name as String.
     */
    private String name;

    /**
     * A List of all Salons located in City.
     */
    @OneToMany(mappedBy = "city")
    private List<Salon> salons;

    /**
     * Non-parameterized constructor for class City.
     */
    public City() {
    }

    /**
     * Parameterized constructor for class City.
     * @param zipCode City's zip code as String
     * @param name City's name as String
     */
    public City(String zipCode, String name) {
        this.zipCode = zipCode;
        this.name = name;
    }

    /**
     * Parameterized constructor for class City.
     * @param name City's name as String
     */
    public City(String name) {
        this.name = name;
    }

    /**
     * Returns city's zip code.
     * @return City's zip code as String.
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets city's zip code.
     * @param zipCode City's zip code as String
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Returns city's name.
     * @return City's name as String.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets city's name.
     * @param name City's name as String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns city's salons.
     * @return City's salons as List<Salon>.
     */
    public List<Salon> getSalons() {
        return salons;
    }

    /**
     * Sets city's salons.
     * @param salons City's salons as List<Salon>
     */
    public void setSalons(List<Salon> salons) {
        this.salons = salons;
    }

    @Override
    public String toString() {
        return "City{" +
                "zipCode='" + zipCode + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
