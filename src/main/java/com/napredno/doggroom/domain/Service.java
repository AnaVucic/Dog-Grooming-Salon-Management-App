package com.napredno.doggroom.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

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
        if (serviceID == null)
            throw new NullPointerException("ID must not be null!");
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
        if (name == null)
            throw new NullPointerException("Name must not be null!");
        if (name.isEmpty())
            throw new IllegalArgumentException("Name must not be empty!");
        if (name.length() < 2 || name.length() > 20 )
            throw new IllegalArgumentException("Name must be between 2 and 20 characters!");
        this.name = name;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        if (fee == null)
            throw new NullPointerException("Fee must not be null!");
        if (fee.compareTo(new BigDecimal(0)) <= 0)
            throw new IllegalArgumentException("Fee must not be less or equal to 0!");
        this.fee = fee;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        if(duration <= 0)
            throw new IllegalArgumentException("Duration must not be less or equal to 0!");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return duration == service.duration && serviceID.equals(service.serviceID) && name.equals(service.name) && fee.equals(service.fee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceID, name, fee, duration);
    }
}
