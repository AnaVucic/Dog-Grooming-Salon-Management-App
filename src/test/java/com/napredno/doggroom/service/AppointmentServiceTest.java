package com.napredno.doggroom.service;

import com.napredno.doggroom.domain.*;
import com.napredno.doggroom.dto.GetAppointmentDTO;
import com.napredno.doggroom.dto.PostAppointmentDTO;
import com.napredno.doggroom.repository.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    List<Appointment> appointments = new ArrayList<>();

    @Mock
    AppointmentRepository appointmentRepository;
    @Mock
    DogRepository dogRepository;
    @Mock
    SalonRepository salonRepository;
    @Mock
    ServiceRepository serviceRepository;
    @Mock
    PersonRepository personRepository;
    @Mock
    ModelMapper modelMapper;


    @InjectMocks
    AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
        Person p = new Person(1L,"Ana", "Vucic", "060 000000");
        Breed b = new Breed(1L,"Westie");
        Dog d = new Dog(1L,p,b,"Meda");
        City c = new City("11000", "Beograd");
        Salon s = new Salon(1L, "Beogradska 23", c);
        Service s1 = new Service(1L,"Bathing", new BigDecimal(100), 10);
        Service s2 = new Service(2L, "Trimming", new BigDecimal(200), 20);
        List<Service> services = new ArrayList<>();
        services.add(s1);
        services.add(s2);
        Appointment a1 = new Appointment();
        a1.setDateTime(LocalDateTime.MAX);
        a1.setSalon(s);
        a1.setDog(d);
        a1.setServices(services);
        appointments.add(a1);
        appointments.add(a1);
    }

    @AfterEach
    void tearDown() {
        appointments = new ArrayList<>();
    }

    @Test
    void getAllAppointmentsOk() {
        when(appointmentRepository.findAll()).thenReturn(appointments);
        when(serviceRepository.findServiceByAppointmentsAppointmentID(appointments.get(0).getAppointmentID())).thenReturn(appointments.get(0).getServices());
        when(modelMapper.map(appointments.get(0), GetAppointmentDTO.class)).thenReturn(new GetAppointmentDTO());

        List<GetAppointmentDTO> getAppointmentDTOs = appointmentService.getAllAppointments();

        assertEquals(2, getAppointmentDTOs.size());
        assertEquals(appointments.get(0).getDog().getName(), getAppointmentDTOs.get(0).getDogName());
        assertEquals(appointments.get(0).getDateTime(), getAppointmentDTOs.get(0).getDateTime());
        assertEquals(appointments.get(0).getDog().getPerson().getAppointmentNumber(),
                getAppointmentDTOs.get(0).getAppointmentNumber());
    }

    @Test
    void addAppointmentOk() {
        Appointment a = appointments.get(0);

        PostAppointmentDTO appointmentDTO = new PostAppointmentDTO();
        appointmentDTO.setDateTime(LocalDateTime.MAX);
        appointmentDTO.setDogID(1L);
        appointmentDTO.setSalonID(1L);
        appointmentDTO.setServiceIDs(Arrays.asList(1L, 2L));

        when(appointmentRepository.save(Mockito.any(Appointment.class))).thenReturn(a);
        when(dogRepository.findById(1L)).thenReturn(Optional.of(a.getDog()));
        when(salonRepository.findById(1L)).thenReturn(Optional.of(a.getSalon()));
        when(serviceRepository.findById(1L)).thenReturn(Optional.of(a.getServices().get(0)));
        when(serviceRepository.findById(2L)).thenReturn(Optional.of(a.getServices().get(1)));

        PostAppointmentDTO savedAppointment = appointmentService.addAppointment(appointmentDTO);

        Assertions.assertThat(savedAppointment).isNotNull();
    }

    @Test
    void addAppointmentNoDog() {
        PostAppointmentDTO appointmentDTO = new PostAppointmentDTO();
        appointmentDTO.setDateTime(LocalDateTime.MAX);
        appointmentDTO.setDogID(100L);
        appointmentDTO.setSalonID(1L);
        appointmentDTO.setServiceIDs(Arrays.asList(1L, 2L));

        when(dogRepository.findById(100L)).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class,
                () -> appointmentService.addAppointment(appointmentDTO));
    }

    @Test
    void addAppointmentNoSalon() {
        PostAppointmentDTO appointmentDTO = new PostAppointmentDTO();
        appointmentDTO.setDateTime(LocalDateTime.MAX);
        appointmentDTO.setDogID(1L);
        appointmentDTO.setSalonID(100L);
        appointmentDTO.setServiceIDs(Arrays.asList(1L, 2L));

        when(salonRepository.findById(100L)).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class,
                () -> appointmentService.addAppointment(appointmentDTO));
    }

    @Test
    void addAppointmentEmptyServices() {
        PostAppointmentDTO appointmentDTO = new PostAppointmentDTO();
        appointmentDTO.setDateTime(LocalDateTime.MAX);
        appointmentDTO.setDogID(1L);
        appointmentDTO.setSalonID(1L);
        appointmentDTO.setServiceIDs(new ArrayList<>());

        assertThrows(IllegalArgumentException.class,
                () -> appointmentService.addAppointment(appointmentDTO));
    }

    @Test
    void addAppointmentInvalidServices() {
        PostAppointmentDTO appointmentDTO = new PostAppointmentDTO();
        appointmentDTO.setDateTime(LocalDateTime.MAX);
        appointmentDTO.setDogID(1L);
        appointmentDTO.setSalonID(1L);
        appointmentDTO.setServiceIDs(Arrays.asList(3L, 4L));

        assertThrows(IllegalArgumentException.class,
                () -> appointmentService.addAppointment(appointmentDTO));
    }

    @Test
    void deleteAppointmentOk() {
        int appointmentNumber = 10;
        Appointment a = appointments.get(0);
        a.getDog().getPerson().setAppointmentNumber(appointmentNumber);

        Long appointmentIDToDelete = 1L;

        when(appointmentRepository.existsById(1L)).thenReturn(true);
        when(appointmentRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(a));
        when(dogRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(a.getDog()));

        appointmentService.deleteAppointment(appointmentIDToDelete);

        assertEquals(9, a.getDog().getPerson().getAppointmentNumber());
    }

    @Test
    void deleteAppointmentNoAppointment(){
        Long appointmentIDToDelete = 100L;
        assertThrows(IllegalArgumentException.class,
                () -> appointmentService.deleteAppointment(appointmentIDToDelete));
    }
}