package com.napredno.doggroom.repository;

import com.napredno.doggroom.domain.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    List<Service> findServiceByAppointmentsAppointmentID(Long AppointmentID);
}
