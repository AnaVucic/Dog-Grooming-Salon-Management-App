package com.napredno.doggroom.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Salon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salon_id")
    private Long salonID;
    private String address;
    @ManyToOne
    @JoinColumn(name = "zip_code")
    private City city;
    public Salon() {
    }

    public Salon(Long salonID, String address, City city) {
        this.salonID = salonID;
        this.address = address;
        this.city = city;
    }

    public Salon(String address, City city) {
        this.address = address;
        this.city = city;
    }

    public Long getSalonID() {
        return salonID;
    }

    public void setSalonID(Long salonID) {
        if (salonID == null)
            throw new NullPointerException("ID must not be null!");
        this.salonID = salonID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null)
            throw new NullPointerException("Address must not be null!");
        if (address.isEmpty())
            throw new IllegalArgumentException("Address must not be empty!");
        if (address.length() < 5 || address.length() > 50 )
            throw new IllegalArgumentException("Address must be between 5 and 50 characters!");
        this.address = address;
    }

    public City getCity() {
        return city;
    }

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
