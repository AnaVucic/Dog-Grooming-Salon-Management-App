package com.napredno.doggroom.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents an appointment to be scheduled.
 * Appointment is identified with appointmentID.
 * Appointment is scheduled on a specific date in specific time. The time and date of an appointment
 * is specified in dateTime field of type LocalDateTime.
 * Appointment is scheduled for a specific dog. The dog who is scheduled for an appointment is
 * specified in dog field of type Dog.
 * Appointment is scheduled in a specific salon. The salon is which the appointment is scheduled is
 * specified in salon field of type Salon.
 * An appointment includes one or more services to be performed. The needed services are specified in
 * services field of type List<Service>.
 * Total fee sums up fees from all included services.
 * Total duration sums up durations from all included services.
 * The Appointment class is annotated with @Entity, indicating that it is a JPA entity.
 */

@Entity
public class Appointment {

    /**
     * Appointment's ID as Long
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long appointmentID;

    /**
     * Appointment's date and time as LocalDateTime
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm")
    private LocalDateTime dateTime;

    /**
     * Appointment's dog of type Dog.
     */
    @ManyToOne
    private Dog dog;

    /**
     * Appointment's salon of type Salon.
     */
    @ManyToOne
    private Salon salon;

    /**
     * Appointment's total fee as BigDecimal, representing a value in RSD.
     */
    private BigDecimal totalFee;

    /**
     * Appointment's total duration as int, representing an estimate in minutes.
     */
    private int totalDuration;

    /**
     * A List of all Services included in Appointment.
     */
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

    /**
     * Non-parameterized constructor for class Appointment.
     */
    public Appointment() {
    }

    /**
     * Parameterized constructor for class Appointment.
     * @param appointmentID Appointment's ID as String
     * @param dateTime Appointment's date and time as LocalDateTime
     * @param dog Appointment's dog of type Dog
     * @param salon Appointment's salon of type Salon
     * @param totalFee Appointment's total dee as BigDecimal
     * @param totalDuration Appointment's total duration as int
     */
    public Appointment(Long appointmentID, LocalDateTime dateTime, Dog dog, Salon salon, BigDecimal totalFee, int totalDuration) {
        this.appointmentID = appointmentID;
        this.dateTime = dateTime;
        this.dog = dog;
        this.salon = salon;
        this.totalFee = totalFee;
        this.totalDuration = totalDuration;
    }

    /**
     * Parameterized constructor for class Appointment.
     * @param dateTime Appointment's date and time as LocalDateTime
     * @param dog Appointment's dog of type Dog
     * @param salon Appointment's salon of type Salon
     * @param totalFee Appointment's total dee as BigDecimal
     * @param totalDuration Appointment's total duration as int
     */
    public Appointment(LocalDateTime dateTime, Dog dog, Salon salon, BigDecimal totalFee, int totalDuration) {
        this.dateTime = dateTime;
        this.dog = dog;
        this.salon = salon;
        this.totalFee = totalFee;
        this.totalDuration = totalDuration;
    }

    /**
     * Returns appointment's ID.
     * @return Appointment's ID as Long.
     */
    public Long getAppointmentID() {
        return appointmentID;
    }

    /**
     * Sets appointment's ID.
     * @param appointmentID Appointment's ID as Long
     */
    public void setAppointmentID(Long appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * Returns appointment's date and time.
     * @return Appointment's date and time as LocalDateTime.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Sets appointment's date and time.
     * @param dateTime Appointment's date and time as LocalDateTime
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Returns appointment's dog.
     * @return Appointment's dog of type Dog.
     */
    public Dog getDog() {
        return dog;
    }

    /**
     * Sets appointment's dog.
     * @param dog Appointment's dog of type Dog
     */
    public void setDog(Dog dog) {
        this.dog = dog;
    }

    /**
     * Returns appointment's salon.
     * @return Appointment's salon of type Salon.
     */
    public Salon getSalon() {
        return salon;
    }

    /**
     * Sets appointment's salon.
     * @param salon Appointment's salon of type Salon
     */
    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    /**
     * Returns appointment's total fee.
     * @return Appointment's total fee as BigDecimal.
     */
    public BigDecimal getTotalFee() {
        return totalFee;
    }

    /**
     * Sets appointment's total fee.
     * @param totalFee Appointment's total fee as BigDecimal
     */
    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    /**
     * Returns appointment's total duration.
     * @return Appointment's total duration as int.
     */
    public int getTotalDuration() {
        return totalDuration;
    }

    /**
     * Sets appointment's total duration.
     * @param totalDuration Appointment's total duration as int
     */
    public void setTotalDuration(int totalDuration) {
        this.totalDuration = totalDuration;
    }

    /**
     * Returns appointment's services.
     * @return Appointment's services as List<Service>.
     */
    public List<Service> getServices() {
        return services;
    }

    /**
     * Sets appointment's services.
     * @param services Appointment's services as List<Service>
     */
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
