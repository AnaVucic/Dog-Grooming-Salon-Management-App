package com.napredno.doggroom.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

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
        this.serviceID = serviceID;
        this.name = name;
        this.fee = fee;
        this.duration = duration;
    }

    /**
     * Parameterized constructor for class Service.
     * @param name Service's name as String
     * @param fee Service's fee as BigDecimal
     * @param duration Service's duration as int
     */
    public Service(String name, BigDecimal fee, int duration) {
        this.name = name;
        this.fee = fee;
        this.duration = duration;
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
     */
    public void setServiceID(Long serviceID) {
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
     */
    public void setName(String name) {
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
     */
    public void setFee(BigDecimal fee) {
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
     */
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
