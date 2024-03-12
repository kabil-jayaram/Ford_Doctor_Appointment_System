package com.example.service;

import com.example.dto.AppointmentDto;
import com.example.entity.Appointment;
import com.example.entity.Doctor;
import com.example.repository.AppointmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class AppointmentServiceTest {

    @Mock
    AppointmentRepository appointmentRepository;

    @InjectMocks
    AppointmentService appointmentService;

    @Test
    void addAppointment() {
        // Given
        Appointment appointment = Appointment.builder().id(1).doctorId(1).patientId(1).build();
        // When
        when(appointmentRepository.save(appointment)).thenReturn(appointment);
        // Then
        Appointment savedAppointment = appointmentService.addAppointment(appointment);
        assertThat(savedAppointment).isNotNull();
        assertThat(savedAppointment).isEqualTo(appointment);
        System.out.println(savedAppointment);
    }

    @Test
    void getAppointmentById() {
        // Given
        int id = 1;
        Appointment appointment = Appointment.builder().id(1).doctorId(1).patientId(1).build();
        appointmentService.addAppointment(appointment);
        // When
        when(appointmentRepository.findById(id)).thenReturn(Optional.of(appointment));
        when(appointmentRepository.save(appointment)).thenReturn(appointment);
        // Then
        Appointment retrievedAppointment = appointmentService.getAppointmentById(id);
        assertThat(retrievedAppointment).isNotNull();
        System.out.println(retrievedAppointment);
    }
    @Test
    void getAppointmentByPatientId() {
        // Given
        int id = 1;
        Appointment appointment = Appointment.builder().id(1).doctorId(1).patientId(1).build();
        appointmentService.addAppointment(appointment);
        // When
        when(appointmentRepository.findAllByPatientId(id)).thenReturn(Arrays.asList(appointment));
        when(appointmentRepository.save(appointment)).thenReturn(appointment);
        // Then
        List<Appointment> retrievedAppointment = appointmentService.getAppointmentByPatientId(id);
        assertThat(retrievedAppointment).isNotNull();
        System.out.println(retrievedAppointment);
    }

    @Test
    void getAppointmentByPatientIdAndStatus() {
        // Given
        int id = 1;
        String status = "Confirmed";
        Appointment appointment = Appointment.builder().id(1).doctorId(1).patientId(1).status("Confirmed").build();
        appointmentService.addAppointment(appointment);
        // When
        when(appointmentRepository.findAllByPatientIdAndStatus(id, status)).thenReturn(Arrays.asList(appointment));
        when(appointmentRepository.save(appointment)).thenReturn(appointment);
        // Then
        List<Appointment> retrievedAppointment = appointmentService.getAppointmentByPatientIdAndStatus(id, status);
        assertThat(retrievedAppointment).isNotNull();
        System.out.println(retrievedAppointment);
    }

    @Test
    void getAppointmentByDoctorId() {
        // Given
        int id = 1;
        Appointment appointment = Appointment.builder().id(1).doctorId(1).patientId(1).status("Confirmed").build();
        appointmentService.addAppointment(appointment);
        // When
        when(appointmentRepository.findAllByDoctorId(id)).thenReturn(Arrays.asList(appointment));
        when(appointmentRepository.save(appointment)).thenReturn(appointment);
        // Then
        List<Appointment> retrievedAppointment = appointmentService.getAppointmentByDoctorId(id);
        assertThat(retrievedAppointment).isNotNull();
        System.out.println(retrievedAppointment);
    }

    @Test
    void getAppointmentByDoctorIdAndStatus() {
        // Given
        int id = 1;
        String status = "Confirmed";
        Appointment appointment = Appointment.builder().id(1).doctorId(1).patientId(1).status("Confirmed").build();
        appointmentService.addAppointment(appointment);
        // When
        when(appointmentRepository.findAllByDoctorIdAndStatus(id, status)).thenReturn(Arrays.asList(appointment));
        when(appointmentRepository.save(appointment)).thenReturn(appointment);
        // Then
        List<Appointment> retrievedAppointment = appointmentService.getAppointmentByDoctorIdAndStatus(id, status);
        assertThat(retrievedAppointment).isNotNull();
        System.out.println(retrievedAppointment);
    }

    @Test
    void getAppointmentByStatus() {
        // Given
        String status = "Confirmed";
        Appointment appointment = Appointment.builder().id(1).doctorId(1).patientId(1).status("Confirmed").build();
        appointmentService.addAppointment(appointment);
        // When
        when(appointmentRepository.findAllByStatus(status)).thenReturn(Arrays.asList(appointment));
        when(appointmentRepository.save(appointment)).thenReturn(appointment);
        // Then
        List<Appointment> retrievedAppointment = appointmentService.getAppointmentByStatus(status);
        assertThat(retrievedAppointment).isNotNull();
        System.out.println(retrievedAppointment);
    }

    @Test
    void updateAppointment() {
        // Given
        int id = 1;
        Appointment originalAppointment = Appointment.builder().id(1).doctorId(1).patientId(1).status("Confirmed").build();
        Appointment appointmentTpUpdate = Appointment.builder().id(1).doctorId(2).patientId(1).status("Cancelled").build();
        appointmentService.addAppointment(originalAppointment);
        // When
        when(appointmentRepository.findById(id)).thenReturn(Optional.of(originalAppointment));
        when(appointmentRepository.save(originalAppointment)).thenReturn(originalAppointment);

        // Then
        Appointment retrievedAppointment = appointmentService.updateAppointment(appointmentTpUpdate);
        assertThat(retrievedAppointment).isNotNull();
        System.out.println(retrievedAppointment);
    }

    @Test
    void deleteAppointment() {
        // Given
        int id = 1;
        Appointment appointment = Appointment.builder().id(1).doctorId(1).patientId(1).status("Confirmed").build();
        appointmentService.addAppointment(appointment);
        // When
        when(appointmentRepository.save(appointment)).thenReturn(appointment);
        // Then
        appointmentService.deleteAppointment(id);
        verify(appointmentRepository, times(1)).deleteById(1);
    }
}