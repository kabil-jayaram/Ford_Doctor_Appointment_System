package com.example.service;


import com.example.dto.DoctorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url = "http://localhost:8080/doctorservice/api",value = "DOCTOR-SERVICE")
public interface APIClient {


    @GetMapping("/doctors")
    public List<DoctorDto> getDoctors();

    @GetMapping("/doctor/specialization")
    public List<DoctorDto> getDoctorsBySpecialization(@RequestParam String specialization);

    @GetMapping("/doctor")
    public DoctorDto getDoctorByIdAndSpecialization(@RequestParam int id, @RequestParam String specialization);

    @GetMapping("/doctor/{id}")
    public DoctorDto getDoctorById(@PathVariable("id") int id);

}
