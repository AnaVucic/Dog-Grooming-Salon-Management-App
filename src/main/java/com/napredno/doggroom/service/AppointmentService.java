package com.napredno.doggroom.service;

import com.napredno.doggroom.domain.Appointment;
import com.napredno.doggroom.domain.Dog;
import com.napredno.doggroom.domain.Person;
import com.napredno.doggroom.domain.Salon;
import com.napredno.doggroom.dto.GetAppointmentDTO;
import com.napredno.doggroom.dto.PostAppointmentDTO;
import com.napredno.doggroom.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Appointment service used for retrieving, scheduling and cancelling appointments.
 *
 * @Author Ana Vucic
 * @since 0.1.0
 */
@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DogRepository dogRepository;
    private final SalonRepository salonRepository;
    private final ServiceRepository serviceRepository;
    private final PersonRepository personRepository;

    private final ModelMapper modelMapper;


    @Autowired
    public AppointmentService (
            AppointmentRepository appointmentRepository,
            DogRepository dogRepository,
            SalonRepository salonRepository,
            ServiceRepository serviceRepository,
            PersonRepository personRepository,
            ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.dogRepository = dogRepository;
        this.salonRepository = salonRepository;
        this.serviceRepository = serviceRepository;
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Returns a list of all appointments currently scheduled in the system.
     *
     * @return All appointments in a List of type GetAppointmentDTO
     */
    public List<GetAppointmentDTO> getAllAppointments(){
        List<Appointment> appointments = new ArrayList<>();
        for (Appointment appointment: appointmentRepository.findAll()) {
            appointment.setServices(serviceRepository.findServiceByAppointmentsAppointmentID(appointment.getAppointmentID()));
            appointments.add(appointment);
        }
        return appointments.stream()
                .map(this::toGetDTO)
                .collect(Collectors.toList());
    }

    /**
     * Schedules a new appointment. Save new appointment in database.
     *
     * @param postAppointmentDTO A DTO of type postAppointmentDTO containing necessary data for scheduling an appointment
     * @return Appointment data that was saved in database
     *
     * @throws IllegalArgumentException dog in PostAppointmentDTO cannot be found in database
     * @throws IllegalArgumentException salon in PostAppointmentDTO cannot be found in database
     * @throws IllegalArgumentException no services have been passed in PostAppointmentDTO
     * @throws IllegalArgumentException service in PostAppointmentDTO cannot be found in database
     */
    public PostAppointmentDTO addAppointment (PostAppointmentDTO postAppointmentDTO){
        Appointment a = new Appointment();

        Optional<Dog> d = dogRepository.findById(postAppointmentDTO.getDogID());
        Optional<Salon> s = salonRepository.findById(postAppointmentDTO.getSalonID());

        if (d.isEmpty()){
            throw new IllegalArgumentException("Dog with id " + postAppointmentDTO.getDogID() + " does not exist!");
        }
        if (s.isEmpty()){
            throw new IllegalArgumentException("Salon with id " + postAppointmentDTO.getSalonID() + " does not exist!");
        }

        a.setDog(d.get());
        a.setSalon(s.get());

        a.setDateTime(postAppointmentDTO.getDateTime());

        // Adding services to an appointment
        List<Long> postedServiceIDs = postAppointmentDTO.getServiceIDs();
        List<Long> invalidServiceIDs = new ArrayList<>();
        if(postedServiceIDs.isEmpty()) {
            throw new IllegalArgumentException("You must select at least one service per appointment!");
        }
        for (Long id : postedServiceIDs) {
            Optional<com.napredno.doggroom.domain.Service> service =
                    serviceRepository.findById(id);
            if(service.isEmpty()) invalidServiceIDs.add(id);
        }
        if(!invalidServiceIDs.isEmpty()) {
            throw new IllegalArgumentException("Services with IDs: " + invalidServiceIDs + " do not exist!");
        }
        List<com.napredno.doggroom.domain.Service> services = serviceRepository
                .findAllById(() -> postAppointmentDTO.getServiceIDs().iterator());

        a.setServices(services);

        // Calculating total duration
        int totalDuration = 0;
        for (com.napredno.doggroom.domain.Service service: services) {totalDuration += service.getDuration();}
        a.setTotalDuration(totalDuration);

        // Calculating total fee
        BigDecimal totalFee = BigDecimal.valueOf(0);
        for (com.napredno.doggroom.domain.Service service: services) {totalFee = totalFee.add(service.getFee());}
        a.setTotalFee(totalFee);

        // Increase appointments number for dog's owner
        Person p = d.get().getPerson();
        p.setAppointmentNumber(p.getAppointmentNumber() + 1);
        personRepository.save(p);

        System.out.println(a);
        appointmentRepository.save(a);
        return postAppointmentDTO;
    }

    /**
     * Cancels a scheduled appointment. Removes it from database.
     *
     * @param appointmentID ID of the appointment to be canceled
     *
     * @throws IllegalArgumentException appointment with given ID cannot be found in database
     */
    public void deleteAppointment(Long appointmentID) {
        boolean exists = appointmentRepository.existsById(appointmentID);
        if (!exists) {
            throw new IllegalArgumentException("Appointment with ID " + appointmentID + "does not exist");
        }

        // Decrease number of appointments for a person after cancelling appointment
        Optional<Dog> dog = dogRepository.findById(appointmentRepository.findById(appointmentID).get().getDog().getDogID());
        Person person = dog.get().getPerson();
        person.setAppointmentNumber(person.getAppointmentNumber() - 1);
        personRepository.save(person);

        appointmentRepository.deleteById(appointmentID);
    }

    private GetAppointmentDTO toGetDTO(Appointment a){

        GetAppointmentDTO dto = modelMapper.map(a, GetAppointmentDTO.class);

        List<String> serviceNames = new ArrayList<>();
        for (com.napredno.doggroom.domain.Service service : a.getServices()) {
            String serviceName = service.getName();
            serviceNames.add(serviceName);
        }
        dto.setServiceNames(serviceNames);
        dto.setDogName(a.getDog().getName());
        dto.setPersonName(a.getDog().getPerson().getFirstname()
                + " "
                + a.getDog().getPerson().getLastname());
        dto.setAppointmentNumber(a.getDog().getPerson().getAppointmentNumber());
        dto.setSalonLocation(a.getSalon().getAddress() + ", " + a.getSalon().getCity().getName());
        return dto;
    }

}
