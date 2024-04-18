package com.napredno.doggroom.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
        if (appointmentID == null)
            throw new NullPointerException("ID must not be null!");
        this.appointmentID = appointmentID;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        if (dateTime == null)
            throw new NullPointerException("Date and time must not be null!");
        if (dateTime.isBefore(LocalDateTime.now().plusHours(24L)))
            throw new IllegalArgumentException("Date and time must not be before 24h from now!");
        this.dateTime = dateTime;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        if (dog == null)
            throw new NullPointerException("Dog must not be null!");
        this.dog = dog;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        if (salon == null)
            throw new NullPointerException("Salon must not be null!");
        this.salon = salon;
    }

    public BigDecimal getTotalFee() { return totalFee; }

    public void setTotalFee(BigDecimal totalFee) {
        if (totalFee == null)
            throw new NullPointerException("Total fee must not be null!");
        if (totalFee.compareTo(new BigDecimal(0)) <= 0)
            throw new IllegalArgumentException("Total fee must not be less or equal to 0!");
        this.totalFee = totalFee;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(int totalDuration) {
        if(totalDuration <= 0)
            throw new IllegalArgumentException("Total duration must not be less or equal to 0!");
        this.totalDuration = totalDuration;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        if (services == null)
            throw new NullPointerException("Services must not be null!");
        if (services.isEmpty())
            throw new IllegalArgumentException("Services size must be at least 1!");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return totalDuration == that.totalDuration && appointmentID.equals(that.appointmentID) && dateTime.equals(that.dateTime) && dog.equals(that.dog) && salon.equals(that.salon) && totalFee.equals(that.totalFee) && services.equals(that.services);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentID, dateTime, dog, salon, totalFee, totalDuration, services);
    }
}
