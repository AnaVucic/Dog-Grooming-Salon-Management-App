package com.napredno.doggroom.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Long serviceID;
    private String name;
    private BigDecimal fee;
    private int duration;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade =
                    {
                            CascadeType.DETACH,
                            CascadeType.MERGE,
                            CascadeType.REFRESH,
                            CascadeType.PERSIST
                    }, mappedBy = "services") // fetch
    private List<Appointment> appointments;

    public Service() {
    }

    public Service(Long serviceID, String name, BigDecimal fee, int duration) {
        this.serviceID = serviceID;
        this.name = name;
        this.fee = fee;
        this.duration = duration;
    }

    public Service(String name, BigDecimal fee, int duration) {
        this.name = name;
        this.fee = fee;
        this.duration = duration;
    }

    public Long getServiceID() {
        return serviceID;
    }

    public void setServiceID(Long serviceID) {
        this.serviceID = serviceID;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Service{" +
                "serviceID=" + serviceID +
                ", name='" + name + '\'' +
                ", fee=" + fee +
                ", duration=" + duration +
                '}';
    }
}
