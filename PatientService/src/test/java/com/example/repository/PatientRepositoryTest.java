package com.example.repository;

import com.example.entity.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class PatientRepositoryTest {
    @MockBean
    private PatientRepository patientRepository;

    @Test
    void addPatient() {
        // Given
        int id = 1;
        Patient patient = Patient.builder().id(1).name("Kabilan").build();
        // When
        when(patientRepository.save(patient)).thenReturn(patient);
        // Then
        Patient savedPatient = patientRepository.save(patient);
        assertThat(savedPatient).isNotNull();
        assertThat(savedPatient.getId()).isEqualTo(id);
        System.out.println(savedPatient);
    }
    
    @Test
    void updatePatient() {
        // Given
        int id = 1;
        Patient originalPatient = Patient.builder().id(1).name("Kabilan").build();
        Patient patientToUpdate = Patient.builder().id(1).name("Tamil").build();
        // When
        when(patientRepository.save(originalPatient)).thenReturn(patientToUpdate);
        when(patientRepository.findById(patientToUpdate.getId())).thenReturn(Optional.of(originalPatient));
        // Then
        Patient retrievedPatient = patientRepository.findById(id).get();
        retrievedPatient.setId(patientToUpdate.getId());
        retrievedPatient.setName(patientToUpdate.getName());
        Patient updatedPatient = patientRepository.save(retrievedPatient);
        assertThat(updatedPatient).isNotNull();
        assertThat(updatedPatient).isEqualTo(patientToUpdate);
        System.out.println(updatedPatient);
    }

    @Test
    void deletePatient() {
        // Given
        int id = 1;
        Patient patient = Patient.builder().id(1).name("Kabilan").build();
        // When
        when(patientRepository.findById(id)).thenReturn(Optional.of(patient));
        // Then
        patientRepository.deleteById(id);
        verify(patientRepository, times(1)).deleteById(id);
    }
}