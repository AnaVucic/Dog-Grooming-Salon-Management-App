package com.napredno.doggroom.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PostAppointmentDTO {

    private Long dogID;
    private Long salonID;
    private LocalDateTime dateTime;
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
