package com.napredno.doggroom.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

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
        this.zipCode = zipCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
}
