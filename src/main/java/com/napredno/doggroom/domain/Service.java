package com.napredno.doggroom.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Represents a service to be included as a part of an Appointment.
 * Service is identified with serviceID.
 * A service must have a name as String.
 * Service fee is represented with a BigDecimal.
 * Service duration represents estimated duration of a service in minutes as int.
 * The Service class is annotated with @Entity, indicating that it is a JPA entity.
 *
 * @author Ana Vucic
 * @since 0.1.0
 */

@Entity
public class Service {

    /**
     * Service's ID as a Long
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Long serviceID;

    /**
     * Service's name as String
     */
    private String name;

    /**
     * Service's fee as BigDecimal, representing a value in RSD.
     */
    private BigDecimal fee;

    /**
     * Service's duration as int, representing an estimate in minutes.
     */
    private int duration;

    /**
     * A List of all Appointments in which a service is included.
     */
    @ManyToMany(fetch = FetchType.LAZY,
            cascade =
                    {
                            CascadeType.DETACH,
                            CascadeType.MERGE,
                            CascadeType.REFRESH,
                            CascadeType.PERSIST
                    }, mappedBy = "services") // fetch
    private List<Appointment> appointments;

    /**
     * Non-parameterized constructor for class Service.
     */
    public Service() {
    }

    /**
     * Parameterized constructor for class Service.
     * @param serviceID Service's ID as Long
     * @param name Service's name as String
     * @param fee Service's fee as BigDecimal
     * @param duration Service's duration as int
     */
    public Service(Long serviceID, String name, BigDecimal fee, int duration) {
//        this.serviceID = serviceID;
//        this.name = name;
//        this.fee = fee;
//        this.duration = duration;
        setServiceID(serviceID);
        setName(name);
        setFee(fee);
        setDuration(duration);
    }

    /**
     * Parameterized constructor for class Service.
     * @param name Service's name as String
     * @param fee Service's fee as BigDecimal
     * @param duration Service's duration as int
     */
    public Service(String name, BigDecimal fee, int duration) {
//        this.name = name;
//        this.fee = fee;
//        this.duration = duration;
        setName(name);
        setFee(fee);
        setDuration(duration);
    }

    /**
     * Returns service's ID.
     * @return Service's ID as Long.
     */
    public Long getServiceID() {
        return serviceID;
    }

    /**
     * Sets service's ID.
     * @param serviceID Service's ID as Long
     * @throws NullPointerException Service ID is null
     */
    public void setServiceID(Long serviceID) {
        if (serviceID == null)
            throw new NullPointerException("ID must not be null!");
        this.serviceID = serviceID;
    }

    /**
     * Returns service's appointments.
     * @return Service's appointments as List<Appointment>.
     */
    public List<Appointment> getAppointments() {
        return appointments;
    }

    /**
     * Sets service's appointments.
     * @param appointments Service's appointments as List<Appointment>
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    /**
     * Returns service's name.
     * @return Service's name as String.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets service's name.
     * @param name Service's name as String
     * @throws NullPointerException Service name is null
     * @throws IllegalArgumentException Service name is an empty String,<br>
     * Service name is not of length 2-20
     */
    public void setName(String name) {
        if (name == null)
            throw new NullPointerException("Name must not be null!");
        if (name.isEmpty())
            throw new IllegalArgumentException("Name must not be empty!");
        if (name.length() < 2 || name.length() > 20 )
            throw new IllegalArgumentException("Name must be between 2 and 20 characters!");
        this.name = name;
    }

    /**
     * Returns service's fee.
     * @return Service's fee as BigDecimal.
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * Sets service's fee.
     * @param fee Service's fee as BigDecimal
     * @throws NullPointerException Service fee is null
     * @throws IllegalArgumentException Service fee is less or equal to 0
     */
    public void setFee(BigDecimal fee) {
        if (fee == null)
            throw new NullPointerException("Fee must not be null!");
        if (fee.compareTo(new BigDecimal(0)) <= 0)
            throw new IllegalArgumentException("Fee must not be less or equal to 0!");
        this.fee = fee;
    }

    /**
     * Returns service's duration.
     * @return Service's duration as int.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets service's duration.
     * @param duration Service's duration as int
     * @throws IllegalArgumentException Service duration is less or equal to 0
     */
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
