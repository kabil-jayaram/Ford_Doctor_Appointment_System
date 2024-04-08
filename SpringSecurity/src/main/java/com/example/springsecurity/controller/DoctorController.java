package com.example.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@CrossOrigin
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

    // below is for all dto controls that are sent to patient microservice
    @GetMapping("/doctors")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Object getDoctorsDto() {
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8082/patientservice/api/doctors", Object.class);

        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }

    @GetMapping("/doctor/{id}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public Object getDoctorDtoById(@PathVariable("id") int id) {
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8082/patientservice/api/doctor/" + id, Object.class);
        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }

    @GetMapping("/doctor/specialization")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public Object getDoctorsDtoBySpecialization(@RequestParam String specialization) {
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8082/patientservice/api/doctor/specialization?specialization=" + specialization, Object.class);

        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }

    @GetMapping("/doctor")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public Object getDoctorDtoByIdAndSpecialization(@RequestParam int id, @RequestParam String specialization) {
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8082/patientservice/api/doctor?id=" + id + "&specialization=" + specialization, Object.class);
        return responseEntity.getBody();
    }

    // below is to fetch patient details from patient microservice

    @PostMapping("/patient")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Object addPatient(@RequestBody Object patient) {
        System.out.println("hey"+patient);
        ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:8082/patientservice/api/patients", patient, Object.class);

        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }

    @PutMapping("/patient")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Object updatePatient(@RequestBody Object patient) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(patient, headers);

        ResponseEntity<Object> responseEntity = restTemplate.exchange("http://localhost:8082/patientservice/api/patients", HttpMethod.PUT, entity, Object.class);

        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }

    @GetMapping("/patients")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Object getPatients() {
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8082/patientservice/api/patients", Object.class);

        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }

    @GetMapping("/patient/{id}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public Object getPatientById(@PathVariable("id") int id) {
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8082/patientservice/api/patient/" + id, Object.class);
        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }

    @GetMapping("/patient/city")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public Object getPatientByCity(@RequestParam String city) {
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8082/patientservice/api/patient/city?city=" + city, Object.class);
        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }

    @GetMapping("/patient/idandname")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public Object getPatientByIdAndName(@RequestParam("id") int id,@RequestParam("name") String name) {
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8082/patientservice/api/patient/idandname?id=" + id+"&name="+name, Object.class);
        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }

    @GetMapping("/patient/age")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public Object getPatientByAge(@RequestParam int age) {
        System.out.println("hey");
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8082/patientservice/api/patient/age?age=" + age, Object.class);
        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }

    @DeleteMapping("/patients/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Object deletePatient(@PathVariable("id") int id) {
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8082/patientservice/api/patients/" + id, HttpMethod.DELETE, null, String.class);
        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();

    }


    //below end points is to get appointment details
    @PostMapping("/appointment")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public Object addAppointment(@RequestBody Object appointment) {
        ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:8080/doctorservice/api/appointment", appointment, Object.class);
        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }

    @PutMapping("/appointment")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Object updateAppointment(@RequestBody Object appointment) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(appointment, headers);

        ResponseEntity<Object> responseEntity = restTemplate.exchange("http://localhost:8080/doctorservice/api/appointment", HttpMethod.PUT, entity, Object.class);

        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }

    @GetMapping("/appointments/{id}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public Object getAppointmentById(@PathVariable("id") int id) {
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8080/doctorservice/api/appointments/" + id, Object.class);
        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }

    @GetMapping("/appointments")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public Object getAllAppointments() {
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8080/doctorservice/api/appointments", Object.class);
        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }

    @GetMapping("/appointments/doctor/{doctorId}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public Object getAppointmentByDoctorid(@PathVariable("doctorId") int doctorId) {
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8080/doctorservice/api/appointments/doctor/" + doctorId, Object.class);
        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }


    @GetMapping("/appointments/doctor")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public Object getAppointmentByDoctorIdAndStatus(@RequestParam("doctorId") int doctorId,@RequestParam("status") String status) {
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8080/doctorservice/api/appointments/doctor?doctorId=" + doctorId+"&status="+status, Object.class);
        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }

    @GetMapping("/appointments/patient/{patientId}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public Object getAppointmentByPatientId(@PathVariable("patientId") int patientId) {
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8080/doctorservice/api/appointments/doctor/" + patientId, Object.class);
        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }

    @GetMapping("/appointments/patient")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public Object getAppointmentByPatientIdAndStatus(@RequestParam("patientId") int patientId,@RequestParam("status") String status) {
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8080/doctorservice/api/appointments/patient?patientId=" + patientId+"&status="+status, Object.class);
        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }

    @GetMapping("/appointments/status")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public Object getAppointmentByStatus(@RequestParam("status") String status) {
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8080/doctorservice/api/appointments/status?status="+status, Object.class);
        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }

    @DeleteMapping("/appointments/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Object deleteAppointment(@PathVariable("id") int id) {
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8080/doctorservice/api/appointments/" + id, HttpMethod.DELETE, null, String.class);
        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
        //    return "test";
    }


}

