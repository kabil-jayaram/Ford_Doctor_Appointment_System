package com.example.controller;

import com.example.entity.Appointment;
import com.example.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/appointments/{id}")
    public Appointment getAppointmentById(@PathVariable("id") int id)
    {
        return appointmentService.getAppointmentById(id);
    }

    @GetMapping("/appointments/doctor/{doctorId}")
    public List<Appointment> getAppointmentByDoctorId(@PathVariable("doctorId") int doctorId)
    {
        return appointmentService.getAppointmentByDoctorId(doctorId);
    }

    @GetMapping("/appointments/patient/{patientId}")
    public Appointment getAppointmentByPatientId(@PathVariable("patientId") int patientId)
    {
        return appointmentService.getAppointmentByPatientId(patientId);
    }

    @GetMapping("/appointments")
    public List<Appointment> getAppointmentByStatus(@RequestBody String status)
    {
        return appointmentService.getAppointmentByStatus(status);
    }

    @DeleteMapping("/appointments/{id}")
    public String deleteAppointment(@PathVariable("id") int id)
    {
        appointmentService.deleteAppointment(id);
        return "Appointment details deleted successfully !!!";
    }

}
