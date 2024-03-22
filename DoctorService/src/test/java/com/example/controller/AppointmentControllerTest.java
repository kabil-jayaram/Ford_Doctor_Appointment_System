package com.example.controller;

import com.example.entity.Appointment;
import com.example.service.AppointmentService;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AppointmentController.class)
class AppointmentControllerTest {
    @MockBean
    private AppointmentService appointmentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAppointmentById() throws Exception {
        // Given
        int id = 1;
        Appointment appointment = Appointment.builder().id(1).patientId(1).doctorId(1).build();
        given(appointmentService.getAppointmentById(id)).willReturn(appointment);
        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/api/appointments/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.patientId").value(1))
                .andExpect(jsonPath("$.doctorId").value(1));

        // Then
        verify(appointmentService, times(1)).getAppointmentById(id);
    }

    @Test
    void getAppointmentByDoctorId() throws Exception {
        // Given
        int doctorId = 1;
        Appointment appointment = Appointment.builder().id(1).patientId(1).doctorId(1).build();
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment);
        given(appointmentService.getAppointmentByDoctorId(doctorId)).willReturn(appointments);
        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/api/appointments/doctor/{id}", doctorId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].patientId").value(1))
                .andExpect(jsonPath("$[0].doctorId").value(1));

        // Then
        verify(appointmentService, times(1)).getAppointmentByDoctorId(doctorId);
    }

    @Test
    void getAppointmentByDoctorIdAndStatus() throws Exception {
        // Given
        int doctorId = 1;
        String status = "Confirmed";
        Appointment appointment = Appointment.builder().id(1).patientId(1).doctorId(1).status("Confirmed").build();
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment);
        given(appointmentService.getAppointmentByDoctorIdAndStatus(doctorId, status)).willReturn(appointments);
        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/api/appointments/doctor")
                        .param("doctorId", String.valueOf(doctorId))
                        .param("status", status)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].patientId").value(1))
                .andExpect(jsonPath("$[0].doctorId").value(1));

        // Then
        verify(appointmentService, times(1)).getAppointmentByDoctorIdAndStatus(doctorId, status);
    }

    @Test
    void getAppointmentByPatientId() throws Exception {
        // Given
        int patientId = 1;
        Appointment appointment = Appointment.builder().id(1).patientId(1).doctorId(1).build();
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment);
        given(appointmentService.getAppointmentByPatientId(patientId)).willReturn(appointments);
        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/api/appointments/patient/{id}", patientId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].patientId").value(1))
                .andExpect(jsonPath("$[0].doctorId").value(1));

        // Then
        verify(appointmentService, times(1)).getAppointmentByPatientId(patientId);
    }

    @Test
    void getAppointmentByPatientIdAndStatus() throws Exception {
        // Given
        int patientId = 1;
        String status = "Confirmed";
        Appointment appointment = Appointment.builder().id(1).patientId(1).doctorId(1).status("Confirmed").build();
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment);
        given(appointmentService.getAppointmentByPatientIdAndStatus(patientId, status)).willReturn(appointments);
        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/api/appointments/patient")
                        .param("patientId", String.valueOf(patientId))
                        .param("status", status)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].patientId").value(1))
                .andExpect(jsonPath("$[0].doctorId").value(1));

        // Then
        verify(appointmentService, times(1)).getAppointmentByPatientIdAndStatus(patientId, status);
    }

    @Test
    void getAppointmentByStatus() throws Exception {
        // Given
        String status = "Confirmed";
        Appointment appointment = Appointment.builder().id(1).patientId(1).doctorId(1).status("Confirmed").build();
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment);
        given(appointmentService.getAppointmentByStatus(status)).willReturn(appointments);
        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/api/appointments")
                        .param("status", status)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].patientId").value(1))
                .andExpect(jsonPath("$[0].doctorId").value(1));

        // Then
        verify(appointmentService, times(1)).getAppointmentByStatus(status);
    }

    @Test
    void deleteAppointment() throws Exception {
        // Given
        int id = 1;
        // When
        given(appointmentService.deleteAppointment(id)).willReturn(true);
        // Then
        mockMvc.perform(delete("/api/appointments/{id}", id))
                .andExpect(status().isOk());
        verify(appointmentService, times(1)).deleteAppointment(id);
    }
}