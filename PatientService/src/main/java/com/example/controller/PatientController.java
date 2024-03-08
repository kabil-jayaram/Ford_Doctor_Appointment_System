package com.example.controller;

import com.example.entity.Address;
import com.example.entity.Patient;
import com.example.service.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class PatientController {
    @Autowired
    private PatientServiceImpl patientService;

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

    @GetMapping("/patient/id/{id}")
    public Patient getPatientById(@PathVariable("id") int id)
    {
        return patientService.getPatientById(id);
    }
    @GetMapping("/patient/city/{city}")
    public Patient getPatientByCity(@PathVariable("city") String city) {
        return patientService.getPatientByCity(city);
    }

    @GetMapping("/patient/{id}/{name}")
    public Patient getIdAndName(@PathVariable("id") int id,@PathVariable("name")String name)
    {
        return patientService.getDoctorByIdAndName(id,name);
    }

    @GetMapping("/patient/diagnosis/{diagnosis}")
    public Patient getPatientByDiagnosis(@PathVariable("diagnosis") String diagnosis)
    {
        return patientService.getPatientByDiagnosis(diagnosis);
    }

    @GetMapping("/patient/age/{age}")
    public Patient getPatientByAge(@PathVariable("age") int age)
    {
        return patientService.getPatientByAge(age);
    }
    @DeleteMapping("/patients/{id}")
    public String deletePatient(@PathVariable("id") int id)
    {
        patientService.deletePatient(id);
        return "Patient Details deleted Successfully";
    }

}
