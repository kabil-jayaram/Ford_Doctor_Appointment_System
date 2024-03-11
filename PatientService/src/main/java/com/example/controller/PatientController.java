package com.example.controller;

import com.example.dto.DoctorDto;
import com.example.entity.Patient;
import com.example.service.DoctorDtoService;
import com.example.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class PatientController {
    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorDtoService doctorDtoService;
    @PostMapping("/patients")
    public Patient addPatient(@RequestBody Patient patient)
    {
        return patientService.addPatient(patient);
    }
    @PutMapping("/patients")
    public Patient updatePatient(@RequestBody Patient patient)
    {
        return patientService.updatePatient(patient);
    }
    @GetMapping("/patients")
    public List<Patient> getPatients()
    {
        return patientService.getAllPatient();
    }

    @GetMapping("/patient/{id}")
    public Patient getPatientById(@PathVariable("id") int id)
    {
        return patientService.getPatientById(id);
    }
    @GetMapping("/patient/city")
    public List<Patient> getPatientByCity(@RequestParam String city) {
        return patientService.getPatientByCity(city);
    }

    @GetMapping("/patient/idandname")
    public Patient getIdAndName(@RequestParam int id,@RequestParam String name)
    {
        return patientService.getDoctorByIdAndName(id,name);
    }

    @GetMapping("/patient/age")
    public List<Patient> getPatientByAge(@RequestParam int age)
    {
        return patientService.getPatientByAge(age);
    }
    @DeleteMapping("/patients/{id}")
    public String deletePatient(@PathVariable("id") int id)
    {
        patientService.deletePatient(id);
        return "Patient Details deleted Successfully";
    }


    // controller for doctor dto


    @GetMapping("/doctors")
    public List<DoctorDto> getDoctors() {
        return doctorDtoService.getDoctors();
    }
    @GetMapping("/doctor/{id}")
    public DoctorDto getDoctorById(@PathVariable("id") int doctorId)
    {
        return doctorDtoService.getDoctorById(doctorId);
    }

    @GetMapping("/doctor/specialization")
    public List<DoctorDto> getDoctorBySpecialization(@RequestParam String specialization) {
        return doctorDtoService.getDoctorsBySpecialization(specialization);
    }

    @GetMapping("/doctor")
    public DoctorDto getDoctorByIdAndSpecialization(@RequestParam int id, @RequestParam String specialization) {
        return doctorDtoService.getDoctorByIdAndSpecialization(id, specialization);
    }

}
