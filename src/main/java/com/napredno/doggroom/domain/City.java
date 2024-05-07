package com.napredno.doggroom.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.Objects;
/**
 * Represents a city that is associated with on object of type Salon.
 * City is identified with zipCode.
 * City must have its name as String.
 * All salons in one city are represented by a List of type Salon.
 * The City class is annotated with @Entity, indicating that it is a JPA entity.
 *
 * @author Ana Vucic
 * @since 0.1.0
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
//        this.zipCode = zipCode;
//        this.name = name;
        setZipCode(zipCode);
        setName(name);
    }

    /**
     * Parameterized constructor for class City.
     * @param name City's name as String
     */
    public City(String name) {
//        this.name = name;
        setName(name);
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
     * @throws NullPointerException Zip code is null
     * @throws IllegalArgumentException Zip code is an empty String,<br>
     *  Zip code length is not 5,<br>
     *  Zip code consists of non-numeric characters<br>
     */
    public void setZipCode(String zipCode) {
        if (zipCode == null)
            throw new NullPointerException("Zip code must not be null!");
        if (zipCode.isEmpty())
            throw new IllegalArgumentException("Zip code must not be empty!");
        if (zipCode.length() != 5)
            throw new IllegalArgumentException("Zip code must exactly 5 characters!");
        if (!zipCode.matches("^[0-9]+$"))
            throw new IllegalArgumentException("Zip code must consist of only numeric characters.");
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
     * @throws NullPointerException City name is null
     * @throws IllegalArgumentException City name is an empty String,<br>
     * City name is not of length 3-30
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return zipCode.equals(city.zipCode) && name.equals(city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, name);
    }
}
