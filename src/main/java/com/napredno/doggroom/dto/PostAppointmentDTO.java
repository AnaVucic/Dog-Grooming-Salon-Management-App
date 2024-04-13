package com.napredno.doggroom.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class is to be used as a Data Transfer Object (DTO) for class Appointment when
 * sending data to API (in POST requests).
 */
public class PostAppointmentDTO {

    /**
     * The ID of the Dog associated with appointment.
     */
    private Long dogID;
    /**
     * The ID of the Salon associated with appointment.
     */
    private Long salonID;
    /**
     * Date and time at which the appointment is to be scheduled.
     */
    private LocalDateTime dateTime;
    /**
     * List of services to be included in the appointment.
     */
    private List<Long> serviceIDs;

    public Long getDogID() {
        return dogID;
    }

    public void setDogID(Long dogID) {
        this.dogID = dogID;
    }

    public Long getSalonID() {
        return salonID;
    }

    public void setSalonID(Long salonID) {
        this.salonID = salonID;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<Long> getServiceIDs() {
        return serviceIDs;
    }

    public void setServiceIDs(List<Long> serviceIDs) {
        this.serviceIDs = serviceIDs;
    }
}
