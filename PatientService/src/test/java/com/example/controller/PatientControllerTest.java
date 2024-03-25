package com.example.controller;

import com.example.entity.Address;
import com.example.entity.Patient;
import com.example.service.DoctorDtoService;
import com.example.service.PatientService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatientController.class)
class PatientControllerTest {

    @MockBean
    private PatientService patientService;

    @MockBean
    private DoctorDtoService doctorDtoService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addPatient() throws Exception {
        // Given
        Patient patient = Patient.builder().id(1).name("Kabilan").build();
        given(patientService.addPatient(patient)).willReturn(patient);
        // ObjectMapping
        ObjectMapper objectMapper = new ObjectMapper();
        String patientJson = objectMapper.writeValueAsString(patient);
        // When
        mockMvc.perform(MockMvcRequestBuilders.post("/api/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(patientJson)) // Include the JSON payload
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Kabilan"));
        // Then
        verify(patientService, times(1)).addPatient(patient);
    }

    @Test
    void updatePatient() throws Exception {
        // Given
        int id = 1;
        Patient originalPatient = Patient.builder().id(1).name("Kabilan").build();
        Patient patientToUpdate = Patient.builder().id(1).name("Harshini").build();
        given(patientService.getPatientById(id)).willReturn(originalPatient);
        given(patientService.addPatient(any(Patient.class))).willReturn(patientToUpdate);

        // Convert the Doctor object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String patientJson = objectMapper.writeValueAsString(patientToUpdate);

        // When
        mockMvc.perform(MockMvcRequestBuilders.put("/api/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(patientJson)) // Include the JSON payload
                .andExpect(status().isOk())
                .andReturn();

        // Then
        verify(patientService, times(1)).updatePatient(patientToUpdate);
        verifyNoMoreInteractions(patientService);
    }

    @Test
    void getPatients() throws Exception {
        // Given
        Patient patient1 = Patient.builder().id(1).name("Kabilan").build();
        Patient patient2 = Patient.builder().id(2).name("Harshini").build();
        List<Patient> patients = new ArrayList<>();
        patients.add(patient1);
        patients.add(patient2);
        given(patientService.getAllPatient()).willReturn(patients);

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/api/patients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // Then
        verify(patientService, times(1)).getAllPatient();
    }

    @Test
    void getPatientById() throws Exception {
        // Given
        int id = 1;
        Patient patient = Patient.builder().id(1).name("Kabilan").build();
        given(patientService.getPatientById(id)).willReturn(patient);

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/api/patient/{id}" ,id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // Then
        verify(patientService, times(1)).getPatientById(id);
    }

    @Test
    void getPatientByCity() throws Exception {
        // Given
        String city = "Chennai";
        Address address = Address.builder().city("Chennai").build();
        Patient patient = Patient.builder().id(1).name("Kabilan").address(address).build();
        List<Patient> patients = new ArrayList<>();
        patients.add(patient);
        given(patientService.getPatientByCity(city)).willReturn(patients);

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/api/patient/city")
                        .param("city", city)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // Then
        verify(patientService, times(1)).getPatientByCity(city);
    }

    @Test
    void getIdAndName() throws Exception {
        // Given
        int id = 1;
        String name = "Kabilan";
        Patient patient = Patient.builder().id(1).name("Kabilan").build();
        given(patientService.getPatientByIdAndName(id, name)).willReturn(patient);

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/api/patient/idandname")
                        .param("id", String.valueOf(id))
                        .param("name", name)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // Then
        verify(patientService, times(1)).getPatientByIdAndName(id, name);
    }

    @Test
    void getPatientByAge() throws Exception {
        // Given
        int age = 22;
        Patient patient = Patient.builder().id(1).name("Kabilan").age(22).build();
        List<Patient> patients = new ArrayList<>();
        patients.add(patient);
        given(patientService.getPatientByAge(age)).willReturn(patients);

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/api/patient/age")
                        .param("age", String.valueOf(age))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // Then
        verify(patientService, times(1)).getPatientByAge(age);
    }

    @Test
    void deletePatient() throws Exception {
        // Given
        int id = 1;
        // When
        given(patientService.deletePatient(id)).willReturn(true);
        // Then
        mockMvc.perform(delete("/api/patients/{id}", id))
                .andExpect(status().isOk());
        verify(patientService, times(1)).deletePatient(id);
    }
}