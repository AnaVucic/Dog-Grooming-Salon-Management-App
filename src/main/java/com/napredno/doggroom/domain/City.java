package com.napredno.doggroom.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.Objects;

@Entity
public class City {

    @Id
    // nema @GeneratedValue jer primarni kljuc (ZipCode) ima unapred odredjenu vresnots
    private String zipCode;
    private String name;
    @OneToMany(mappedBy = "city")
    private List<Salon> salons;

    public City() {
    }

    public City(String zipCode, String name) {
        this.zipCode = zipCode;
        this.name = name;
    }

    public City(String name) {
        this.name = name;
    }

    public String getZipCode() {
        return zipCode;
    }

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

    public List<Salon> getSalons() {
        return salons;
    }

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
