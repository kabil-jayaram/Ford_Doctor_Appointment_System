package com.example.controller;

import com.example.entity.Doctor;
import com.example.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/doctors")
    public List<Doctor> getDoctors()
    {
        return doctorService.getAllDoctor();
    }

    @GetMapping("/doctor/{id}")
    public Doctor getDoctorById(@PathVariable("id") int id)
    {
        return doctorService.getDoctoryById(id);
    }

    @GetMapping("/doctor/specialization")
    public Doctor getDoctorBySpecialization(@RequestBody Doctor doctor)
    {
        return doctorService.getDoctorBySpecialization(doctor.getSpecialization());
    }

    @GetMapping("/doctor")
    public Doctor getDoctorByIdAndSpecialization(@RequestBody Doctor doctor)
    {
        return doctorService.getDoctorByIdAndSpecialization(doctor.getId(), doctor.getSpecialization());
    }
    @DeleteMapping("/doctor/{id}")
    public String deleteDoctor(@PathVariable("id") int id)
    {
        doctorService.deleteDoctor(id);
        return "Doctor details deleted successfully !!!";
    }

}
