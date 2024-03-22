package com.example.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class DoctorController {
    @Autowired
    private RestTemplate restTemplate;

    // below is for doctor controls

    @PostMapping("/doctor")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Object addDoctor(@RequestBody Object doctor) {
        ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:8080/doctorservice/api/doctors", doctor, Object.class);

        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }

    @PutMapping("/doctor")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Object updateDoctor(@RequestBody Object doctor) {
        System.out.println("test");
        // return "test";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(doctor, headers);

        ResponseEntity<Object> responseEntity = restTemplate.exchange("http://localhost:8080/doctorservice/api/doctors",HttpMethod.PUT, entity,Object.class);

        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }

    @DeleteMapping("/doctor/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Object deleteDoctor(@PathVariable("id") int id) {
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8080/doctorservice/api/doctor/" + id, HttpMethod.DELETE, null, String.class);
        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
        //    return "test";
    }

    // below is for all dto controls
    @GetMapping("/doctors")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Object getDoctorsDto() {
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8080/doctorservice/api/doctors", Object.class);

        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }

    @GetMapping("/doctor/{id}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public Object getDoctorDtoById(@PathVariable("id") int id) {
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8080/doctorservice/api/doctor/" + id, Object.class);
        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }

    @GetMapping("/doctor/specialization")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public Object getDoctorsDtoBySpecialization(@RequestParam String specialization) {
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8080/doctorservice/api/doctor/specialization?specialization=" + specialization, Object.class);

        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }

    @GetMapping("/doctor")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public Object getDoctorDtoByIdAndSpecialization(@RequestParam int id, @RequestParam String specialization) {
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8080/doctorservice/api/doctor?id=" + id + "&specialization=" + specialization, Object.class);
        return responseEntity.getBody();
    }
}

