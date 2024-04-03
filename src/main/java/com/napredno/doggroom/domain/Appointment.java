package com.napredno.doggroom.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long appointmentID;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm")
    private LocalDateTime dateTime;

    @ManyToOne
    private Dog dog;

    @ManyToOne
    private Salon salon;

    private BigDecimal totalFee;

    private int totalDuration;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade =
                    {
                            CascadeType.DETACH,
                            CascadeType.MERGE,
                            CascadeType.REFRESH,
                            CascadeType.PERSIST
                    })
    @JoinTable(
            name = "appointment_service",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<Service> services;

    public Appointment() {
    }

    public Appointment(Long appointmentID, LocalDateTime dateTime, Dog dog, Salon salon, BigDecimal totalFee, int totalDuration) {
        this.appointmentID = appointmentID;
        this.dateTime = dateTime;
        this.dog = dog;
        this.salon = salon;
        this.totalFee = totalFee;
        this.totalDuration = totalDuration;
    }

    public Appointment(LocalDateTime dateTime, Dog dog, Salon salon, BigDecimal totalFee, int totalDuration) {
        this.dateTime = dateTime;
        this.dog = dog;
        this.salon = salon;
        this.totalFee = totalFee;
        this.totalDuration = totalDuration;
    }

    public Long getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(Long appointmentID) {
        this.appointmentID = appointmentID;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(int totalDuration) {
        this.totalDuration = totalDuration;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }




    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentID=" + appointmentID +
                ", dateTime=" + dateTime +
                ", dog=" + dog +
                ", salon=" + salon +
                ", totalFee=" + totalFee +
                ", totalDuration=" + totalDuration +
                '}';
    }
}
