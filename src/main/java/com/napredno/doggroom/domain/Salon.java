package com.napredno.doggroom.domain;

import jakarta.persistence.*;

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
        this.salonID = salonID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
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
}
