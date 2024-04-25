package com.napredno.doggroom.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
 *
 * @author Ana Vucic
 * @since 0.1.0
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
     * @throws NullPointerException Appointment ID is null
     */
    public void setAppointmentID(Long appointmentID) {
        if (appointmentID == null)
            throw new NullPointerException("ID must not be null!");
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
     * @throws NullPointerException Date and time is null
     * @throws IllegalArgumentException Date and time are set to a timestamp less than 24h from now
     */
    public void setDateTime(LocalDateTime dateTime) {
        if (dateTime == null)
            throw new NullPointerException("Date and time must not be null!");
        if (dateTime.isBefore(LocalDateTime.now().plusHours(24L)))
            throw new IllegalArgumentException("Date and time must not be before 24h from now!");
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
     * @throws NullPointerException Dog is null
     */
    public void setDog(Dog dog) {
        if (dog == null)
            throw new NullPointerException("Dog must not be null!");
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
     * @throws NullPointerException Salon is null
     */
    public void setSalon(Salon salon) {
        if (salon == null)
            throw new NullPointerException("Salon must not be null!");
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
     * @throws NullPointerException Total fee is null
     * @throws IllegalArgumentException Total fee is less or equal to 0
     */
    public void setTotalFee(BigDecimal totalFee) {
        if (totalFee == null)
            throw new NullPointerException("Total fee must not be null!");
        if (totalFee.compareTo(new BigDecimal(0)) <= 0)
            throw new IllegalArgumentException("Total fee must not be less or equal to 0!");
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
     * @throws IllegalArgumentException Total duration is less or equal to 0
     */
    public void setTotalDuration(int totalDuration) {
        if(totalDuration <= 0)
            throw new IllegalArgumentException("Total duration must not be less or equal to 0!");
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
     * @throws NullPointerException Service list is null
     * @throws IllegalArgumentException Service list is empty
     */
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
