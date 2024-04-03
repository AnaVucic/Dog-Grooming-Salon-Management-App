package com.napredno.doggroom.controller;

import com.napredno.doggroom.dto.GetAppointmentDTO;
import com.napredno.doggroom.dto.PostAppointmentDTO;
import com.napredno.doggroom.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }


    @GetMapping("")
    @ResponseBody
    public List<GetAppointmentDTO> getAllAppointments(){
        return appointmentService.getAllAppointments();
    }

    @PostMapping("")
    @ResponseBody
    public PostAppointmentDTO addAppointment(@RequestBody PostAppointmentDTO postAppointmentDTO){
        return appointmentService.addAppointment(postAppointmentDTO);
    }

    @DeleteMapping(path = "{appointmentID}")
    public void deleteAppointment(
            @PathVariable("appointmentID") Long appointmentID
    ){
        appointmentService.deleteAppointment(appointmentID);
    }



}
