package com.example.service;

import com.example.dto.DoctorDto;
import com.example.entity.Doctor;
import com.example.entity.TimeSlot;
import com.example.repository.DoctorRepository;
import com.example.repository.TimeSlotRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class DoctorServiceTest {
    @Mock
    DoctorRepository doctorRepository;

    @Mock
    TimeSlotRepository timeSlotRepository;

    @InjectMocks
    DoctorService doctorService;

    @Test
    void addDoctor() {
        // Given
        Doctor doctor = Doctor.builder().id(1).name("Kabilan").build();
        // When
        when(doctorRepository.save(doctor)).thenReturn(doctor);
        // Then
        Doctor savedDoctor = doctorService.addDoctor(doctor);
        assertThat(savedDoctor).isNotNull();
        assertThat(savedDoctor).isEqualTo(doctor);
        System.out.println(savedDoctor);
    }

    @Test
    void updateDoctor() {
        // Given
        List<TimeSlot> timeSlot = new ArrayList<>();
        timeSlot.add(TimeSlot.builder().id(1).startTime("9:00 AM").endTime("12:00 PM").available(true).build());
        Doctor originalDoctor = Doctor.builder().id(1).name("Kabilan").timeSlot(timeSlot).build();
        Doctor doctorToUpdate = Doctor.builder().id(1).name("Harshini").timeSlot(timeSlot).build();
        doctorService.addDoctor(originalDoctor);
        // When
        when(doctorRepository.findById(doctorToUpdate.getId())).thenReturn(Optional.of(originalDoctor));
        when(doctorRepository.save(originalDoctor)).thenReturn(originalDoctor);
        // Then
        Doctor updatedDoctor = doctorService.updateDoctor(doctorToUpdate);
        assertThat(updatedDoctor).isNotNull();
        assertThat(updatedDoctor).isEqualTo(doctorToUpdate);
        System.out.println(updatedDoctor);
    }

    @Test
    void getAllDoctor() {
        // Given
        List<TimeSlot> timeSlot = new ArrayList<>();
        timeSlot.add(TimeSlot.builder().id(1).startTime("9:00 AM").endTime("12:00 PM").available(true).build());
        Doctor doctor1 = Doctor.builder().id(1).name("Kabilan").timeSlot(timeSlot).build();
        Doctor doctor2 = Doctor.builder().id(2).name("Harshini").timeSlot(timeSlot).build();
        doctorService.addDoctor(doctor1);
        doctorService.addDoctor(doctor2);
        // When
        when(doctorRepository.findAll()).thenReturn(Arrays.asList(doctor1, doctor2));
        when(doctorRepository.save(doctor1)).thenReturn(doctor1);
        // Then
        List<DoctorDto> doctors = doctorService.getAllDoctor();
        assertThat(doctors).isNotNull();
        System.out.println(doctors);
    }

    @Test
    void getDoctoryById() {
        // Given
        int id = 1;
        Doctor doctor = Doctor.builder().id(1).name("Kabilan").build();
        doctorService.addDoctor(doctor);
        // When
        when(doctorRepository.findById(id)).thenReturn(Optional.of(doctor));
        // Then
        DoctorDto retrievedDoctor = doctorService.getDoctoryById(id);
        assertThat(retrievedDoctor).isNotNull();
        System.out.println(retrievedDoctor);
    }

    @Test
    void getDoctorBySpecialization() {
        // Given
        String specialization = "Cardiology";
        Doctor doctor = Doctor.builder().id(2).name("Harshini").specialization("Cardiology").build();
        doctorService.addDoctor(doctor);
        // When
        when(doctorRepository.findAllBySpecialization(specialization)).thenReturn(Arrays.asList(doctor));
        // Then
        List<DoctorDto> doctors = doctorService.getDoctorBySpecialization(specialization);
        assertThat(doctors).isNotNull();
        System.out.println(doctors);
    }

    @Test
    void getDoctorByIdAndSpecialization() {
        // Given
        Doctor doctor1 = Doctor.builder().id(1).name("Kabilan").specialization("Cardiology").build();
        Doctor doctor2 = Doctor.builder().id(2).name("Harshini").specialization("Neurology").build();
        doctorService.addDoctor(doctor1);
        doctorService.addDoctor(doctor2);
        // When
        when(doctorRepository.save(doctor1)).thenReturn(doctor1);
        when(doctorRepository.findByIdAndSpecialization(doctor1.getId(), doctor1.getSpecialization()))
                .thenReturn(doctor1);
        // Then
        DoctorDto retrievedDoctor = doctorService.getDoctorByIdAndSpecialization(1, "Cardiology");
        assertThat(retrievedDoctor).isNotNull();
        System.out.println(retrievedDoctor);
    }

    @Test
    void deleteDoctor() {
        // Given
        Doctor doctor = Doctor.builder().id(1).name("Kabilan").specialization("Cardiology").build();
        doctorService.addDoctor(doctor);
        // When
        when(doctorRepository.save(doctor)).thenReturn(doctor);
        // Then
        doctorService.deleteDoctor(1);
        verify(doctorRepository, times(1)).deleteById(1);
    }
}