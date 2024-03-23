package com.example.service;

import com.example.entity.Address;
import com.example.entity.Patient;
import com.example.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {
    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;
    @Test
    void addPatient() {
        // Given
        Patient patient = Patient.builder().id(1).name("Kabilan").build();
        // When
        when(patientRepository.save(patient)).thenReturn(patient);
        // Then
        Patient savedPatient = patientService.addPatient(patient);
        assertThat(savedPatient).isNotNull();
        assertThat(savedPatient).isEqualTo(patient);
        System.out.println(savedPatient);
    }

    @Test
    void updatePatient() {
        // Given
        Patient originalPatient = Patient.builder().id(1).name("Kabilan").build();
        Patient patientToUpdate = Patient.builder().id(1).name("Tamil").build();
        // When
        when(patientRepository.save(originalPatient)).thenReturn(patientToUpdate);
        when(patientRepository.findById(patientToUpdate.getId())).thenReturn(Optional.of(originalPatient));
        // Then
        Patient updatedPatient = patientService.updatePatient(patientToUpdate);
        assertThat(updatedPatient).isNotNull();
        assertThat(updatedPatient).isEqualTo(patientToUpdate);
        System.out.println(updatedPatient);
    }

    @Test
    void getAllPatient() {
        // Given
        Patient patient1 = Patient.builder().id(1).name("Kabilan").build();
        Patient patient2 = Patient.builder().id(2).name("Tamil").build();
        List<Patient> patients = new ArrayList<>();
        patients.add(patient1);
        patients.add(patient2);
        // When
        when(patientRepository.findAll()).thenReturn(patients);
        // Then
        List<Patient> retrievedPatients = patientService.getAllPatient();
        assertThat(retrievedPatients).isNotNull();
        assertThat(retrievedPatients).isEqualTo(patients);
        System.out.println(retrievedPatients);
    }

    @Test
    void getPatientById() {
        // Given
        int id = 1;
        Patient patient = Patient.builder().id(1).name("Kabilan").build();
        // When
        when(patientRepository.findById(id)).thenReturn(Optional.of(patient));
        // Then
        Patient retrievedPatient = patientService.getPatientById(id);
        assertThat(retrievedPatient).isNotNull();
        assertThat(retrievedPatient).isEqualTo(patient);
        System.out.println(retrievedPatient);
    }

    @Test
    void getPatientByCity() {
        // Given
        String city = "Chennai";
        Address address = Address.builder().id(1).city("Chennai").build();
        Patient patient = Patient.builder().id(1).name("Kabilan").address(address).build();
        List<Patient> patients = new ArrayList<>();
        patients.add(patient);
        // When
        when(patientRepository.findAllByAddressCity(city)).thenReturn(patients);
        // Then
        List<Patient> retrievedPatient = patientService.getPatientByCity(city);
        assertThat(retrievedPatient).isNotNull();
        assertThat(retrievedPatient).isEqualTo(patients);
        System.out.println(retrievedPatient);
    }

    @Test
    void getPatientByIdAndName() {
        // Given
        int id = 1;
        String name = "Kabilan";
        Patient patient = Patient.builder().id(1).name("Kabilan").build();
        // When
        when(patientRepository.findByIdAndName(id, name)).thenReturn(patient);
        // Then
        Patient retrievedPatient = patientService.getPatientByIdAndName(id, name);
        assertThat(retrievedPatient).isNotNull();
        assertThat(retrievedPatient).isEqualTo(patient);
        System.out.println(retrievedPatient);
    }

    @Test
    void getPatientByAge() {
        // Given
        int age = 22;
        Patient patient = Patient.builder().id(1).name("Kabilan").age(22).build();
        List<Patient> patients = new ArrayList<>();
        patients.add(patient);
        // When
        when(patientRepository.findAllByAge(age)).thenReturn(patients);
        // Then
        List<Patient> retrievedPatient = patientService.getPatientByAge(age);
        assertThat(retrievedPatient).isNotNull();
        assertThat(retrievedPatient).isEqualTo(patients);
        System.out.println(retrievedPatient);
    }

    @Test
    void deletePatient() {
        // Given
        int id = 1;
        Patient patient = Patient.builder().id(1).name("Kabilan").age(22).build();
        patientService.addPatient(patient);
        // When
        willDoNothing().given(patientRepository).deleteById(id);
        // Then
        patientService.deletePatient(id);
        verify(patientRepository, times(1)).deleteById(id);
    }
}