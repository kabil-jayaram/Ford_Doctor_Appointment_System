package com.example.controller;

import com.example.dto.DoctorDto;
import com.example.entity.Doctor;
import com.example.service.DoctorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DoctorController.class)
class DoctorControllerTest {
    @MockBean
    private DoctorService doctorService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addDoctors() throws Exception {
        // Given
        Doctor doctor = Doctor.builder().id(1).name("Kabilan").build();
        given(doctorService.addDoctor(doctor)).willReturn(doctor);

        // Convert the Doctor object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String doctorJson = objectMapper.writeValueAsString(doctor);

        // When
        mockMvc.perform(MockMvcRequestBuilders.post("/api/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(doctorJson)) // Include the JSON payload
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Kabilan"));

        // Then
        verify(doctorService, times(1)).addDoctor(doctor);
    }


    @Test
    void updateDoctors() throws Exception {
        // Given
        int id = 1;
        DoctorDto originalDoctor = DoctorDto.builder().id(1).name("Kabilan").build();
        Doctor doctorToUpdate = Doctor.builder().id(1).name("Harshini").build();
        given(doctorService.getDoctorById(id)).willReturn(originalDoctor);
        given(doctorService.addDoctor(any(Doctor.class))).willReturn(doctorToUpdate);

        // Convert the Doctor object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String doctorJson = objectMapper.writeValueAsString(doctorToUpdate);

        // When
        mockMvc.perform(MockMvcRequestBuilders.put("/api/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(doctorJson)) // Include the JSON payload
                .andExpect(status().isOk())
                .andReturn();

        // Then
        verify(doctorService, times(1)).updateDoctor(doctorToUpdate);
        verifyNoMoreInteractions(doctorService);
    }

    @Test
    void getDoctors() throws Exception {
        // Given
        DoctorDto doctor1 = DoctorDto.builder().id(1).name("Kabilan").build();
        DoctorDto doctor2 = DoctorDto.builder().id(2).name("Harshini").build();
        List<DoctorDto> doctors = new ArrayList<>();
        doctors.add(doctor1);
        doctors.add(doctor2);
        given(doctorService.getAllDoctor()).willReturn(doctors);
        // When
        mockMvc.perform(get("/api/doctors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Kabilan"));
        // Then
        verify(doctorService, times(1)).getAllDoctor();
    }

    @Test
    void getDoctorById() throws Exception {
        // Given
        int id = 1;
        DoctorDto doctor = DoctorDto.builder().id(1).name("Kabilan").build();
        given(doctorService.getDoctorById(id)).willReturn(doctor);
        // When
        mockMvc.perform(get("/api/doctor/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        // Then
        verify(doctorService, times(1)).getDoctorById(id);
    }

    @Test
    void getDoctorBySpecialization() throws Exception {
        // Given
        String specialization = "Cardio";
        List<DoctorDto> doctors = new ArrayList<>();
        DoctorDto doctor = DoctorDto.builder().id(1).name("Kabilan").specialization("Cardio").build();
        doctors.add(doctor);
        given(doctorService.getDoctorBySpecialization(specialization)).willReturn(doctors);
        // When
        mockMvc.perform(get("/api/doctor/specialization").param("specialization", specialization))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        // Then
        verify(doctorService, times(1)).getDoctorBySpecialization(specialization);
    }

    @Test
    void getDoctorByIdAndSpecialization() throws Exception {
        // Given
        int id = 1;
        String specialization = "Cardio";
        DoctorDto doctor = DoctorDto.builder().id(1).name("Kabilan").specialization("Cardio").build();
        given(doctorService.getDoctorByIdAndSpecialization(id, specialization)).willReturn(doctor);
        // When
        mockMvc.perform(get("/api/doctor").param("id", String.valueOf(id)).param("specialization", specialization))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        // Then
        verify(doctorService, times(1)).getDoctorByIdAndSpecialization(id, specialization);
    }

    @Test
    void deleteDoctor() throws Exception {
        // Given
        int id = 1;
        // When
        given(doctorService.deleteDoctor(id)).willReturn(true);
        // Then
        mockMvc.perform(delete("/api/doctor/{id}", id))
                .andExpect(status().isOk());
        verify(doctorService, times(1)).deleteDoctor(id);
    }
}