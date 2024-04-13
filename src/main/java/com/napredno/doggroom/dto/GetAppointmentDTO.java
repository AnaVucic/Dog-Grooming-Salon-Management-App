package com.napredno.doggroom.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * This class is to be used as a Data Transfer Object (DTO) for class Appointment when
 * retrieving data from API (in GET requests).
 */
public class GetAppointmentDTO {

    /**
     * Name of the dog who has a scheduled appointment.
     */
    private String dogName;
    /**
     * Name of the owner of the dog who has a scheduled appointment.
     */
    private String personName;
    /**
     * Number of appointments that the owner has scheduled so far.
     */
    private int appointmentNumber;

    /**
     * Location of the salon where appointment is scheduled.
     */
    private String salonLocation;
    /**
     * Date and time of the appointment.
     */
    private LocalDateTime dateTime;

    /**
     * List of all the services included in the appointment.
     */
    private List<String> serviceNames;

    /**
     * Total fee of the appointment.
     */
    private BigDecimal totalFee;
    /**
     * Total duration of the appointment.
     */
    private int totalDuration;

    //removed constructors

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getAppointmentNumber() {
        return appointmentNumber;
    }

    public void setAppointmentNumber(int appointmentNumber) {
        this.appointmentNumber = appointmentNumber;
    }

    public String getSalonLocation() {
        return salonLocation;
    }

    public void setSalonLocation(String salonLocation) {
        this.salonLocation = salonLocation;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<String> getServiceNames() {
        return serviceNames;
    }

    public void setServiceNames(List<String> serviceNames) {
        this.serviceNames = serviceNames;
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
}
