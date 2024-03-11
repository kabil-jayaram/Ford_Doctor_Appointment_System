package com.example;

import com.example.entity.Patient;
import com.example.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import javax.persistence.Table;

import java.util.Optional;

@SpringBootTest
public class PatientSystemApplicationRepositoryTests {
    @Mock
    PatientRepository patientRepository;
    @Test
    void addPatient()
    {
        Patient patient = Patient.builder().id(1).name("Harshini").age(20).build();
        when(patientRepository.save(patient)).thenReturn(patient);

        Patient savedPatient = patientRepository.save(patient);
        assertThat(savedPatient).isNotNull();
        assertThat(savedPatient).isEqualTo(patient);
        System.out.println(patient);
    }
    @Test
    void updatePatient() {
        // Given
        Patient   originalPatient = Patient.builder().id(1).name("Kabilan").age(21).build();
        Patient patientToUpdate = Patient.builder().id(1).name("Harshini").age(21).build();
        patientRepository.save(originalPatient);
        // When
        when(patientRepository.findById(patientToUpdate.getId())).thenReturn(Optional.of(originalPatient));
        when(patientRepository.save(originalPatient)).thenReturn(patientToUpdate);


        // Then
        Patient retrievedPatient = patientRepository.findById(patientToUpdate.getId()).orElseThrow();
        retrievedPatient.setName(patientToUpdate.getName());
        retrievedPatient.setAge(patientToUpdate.getAge());
        Patient updatedPatient = patientRepository.save(retrievedPatient);
        assertThat(updatedPatient).isNotNull();
        assertThat(updatedPatient.getId()).isEqualTo(patientToUpdate.getId());
        assertThat(updatedPatient.getName()).isEqualTo(patientToUpdate.getName());
        System.out.println(updatedPatient);
    }
    @Test
    void deletePatient() {
        // Given
        Patient patient = Patient.builder().id(1).name("Kabilan").age(21).build();

        // When
        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));

        // Then
        patientRepository.deleteById(patient.getId());
        verify(patientRepository, times(1)).deleteById(patient.getId());
    }
}
