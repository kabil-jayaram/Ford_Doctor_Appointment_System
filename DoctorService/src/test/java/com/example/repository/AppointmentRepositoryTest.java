package com.example.repository;

import com.example.entity.Appointment;
import com.example.entity.Appointment;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class AppointmentRepositoryTest {
    @Mock
    AppointmentRepository appointmentRepository;

    @Test
    void addAppointment() {
        // Given
        Appointment appointment = Appointment.builder().id(1).doctorId(1).patientId(1).status("Confirmed").build();
        // When
        when(appointmentRepository.save(appointment)).thenReturn(appointment);

        // Then
        Appointment savedAppointment = appointmentRepository.save(appointment);
        assertThat(savedAppointment).isNotNull();
        assertThat(savedAppointment).isEqualTo(appointment);
        System.out.println(appointment);
    }

    @Test
    void updateAppointment() {
        // Given
        Appointment originalAppointment = Appointment.builder().id(1).doctorId(1).patientId(1).status("Confirmed").build();
        Appointment appointmentToUpdate = Appointment.builder().id(1).doctorId(2).patientId(1).status("Confirmed").build();
        appointmentRepository.save(originalAppointment);
        // When
        when(appointmentRepository.findById(appointmentToUpdate.getId())).thenReturn(Optional.of(originalAppointment));
        when(appointmentRepository.save(originalAppointment)).thenReturn(appointmentToUpdate);


        // Then
        Appointment retrievedAppointment = appointmentRepository.findById(appointmentToUpdate.getId()).orElseThrow();
        retrievedAppointment.setId(appointmentToUpdate.getId());
        retrievedAppointment.setDoctorId(appointmentToUpdate.getDoctorId());
        retrievedAppointment.setPatientId(appointmentToUpdate.getPatientId());
        retrievedAppointment.setStatus(appointmentToUpdate.getStatus());
        Appointment updatedAppointment = appointmentRepository.save(retrievedAppointment);
        assertThat(updatedAppointment).isNotNull();
        assertThat(updatedAppointment.getId()).isEqualTo(appointmentToUpdate.getId());
        System.out.println(updatedAppointment);
    }

    @Test
    void deleteAppointment() {
        // Given
        Appointment appointment = Appointment.builder().id(1).doctorId(1).patientId(1).status("Confirmed").build();

        // When
        when(appointmentRepository.findById(appointment.getId())).thenReturn(Optional.of(appointment));

        // Then
        appointmentRepository.deleteById(appointment.getId());
        verify(appointmentRepository, times(1)).deleteById(appointment.getId());
    }
}