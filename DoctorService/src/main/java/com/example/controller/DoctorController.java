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

    @PostMapping("/doctors")
    public Doctor addDoctors(@RequestBody Doctor doctor) {
        return doctorService.addDoctor(doctor);
    }

    @PutMapping("/doctors")
    public Doctor updateDoctors(@RequestBody Doctor doctor) {
        return doctorService.updateDoctor(doctor);
    }

    @GetMapping("/doctors")
    public List<Doctor> getDoctors() {
        return doctorService.getAllDoctor();
    }

    @GetMapping("/doctor/{id}")
    public Doctor getDoctorById(@PathVariable("id") int id) {
        return doctorService.getDoctoryById(id);
    }

    @GetMapping("/doctor/specialization")
    public Doctor getDoctorBySpecialization(@RequestParam String specialization) {
        return doctorService.getDoctorBySpecialization(specialization);
    }

    @GetMapping("/doctor")
    public Doctor getDoctorByIdAndSpecialization(@RequestParam int id, @RequestParam String specialization) {
        return doctorService.getDoctorByIdAndSpecialization(id, specialization);
    }

    @DeleteMapping("/doctor/{id}")
    public String deleteDoctor(@PathVariable("id") int id) {
        doctorService.deleteDoctor(id);
        return "Doctor details deleted successfully !!!";
    }

}
