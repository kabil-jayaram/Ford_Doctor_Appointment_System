package com.example.repository;

import com.example.entity.Doctor;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@SpringBootTest
class DoctorRepositoryTest {
	@Mock
	DoctorRepository doctorRepository;

	@Test
	void addDoctor() {
		// Given
		Doctor doctor = Doctor.builder().id(1).name("Kabilan").specialization("Cardiology").build();
		// When
		when(doctorRepository.save(doctor)).thenReturn(doctor);

		// Then
		Doctor savedDoctor = doctorRepository.save(doctor);
		assertThat(savedDoctor).isNotNull();
		assertThat(savedDoctor).isEqualTo(doctor);
		System.out.println(doctor);
	}

	@Test
	void updateDoctor() {
		// Given
		Doctor originalDoctor = Doctor.builder().id(1).name("Kabilan").specialization("Cardiology").build();
		Doctor doctorToUpdate = Doctor.builder().id(1).name("Harshini").specialization("Cardiology").build();
		doctorRepository.save(originalDoctor);
		// When
		when(doctorRepository.findById(doctorToUpdate.getId())).thenReturn(Optional.of(originalDoctor));
		when(doctorRepository.save(originalDoctor)).thenReturn(doctorToUpdate);


		// Then
		Doctor retrievedDoctor = doctorRepository.findById(doctorToUpdate.getId()).orElseThrow();
		retrievedDoctor.setName(doctorToUpdate.getName());
		retrievedDoctor.setSpecialization(doctorToUpdate.getSpecialization());
		Doctor updatedDoctor = doctorRepository.save(retrievedDoctor);
		assertThat(updatedDoctor).isNotNull();
		assertThat(updatedDoctor.getId()).isEqualTo(doctorToUpdate.getId());
		assertThat(updatedDoctor.getName()).isEqualTo(doctorToUpdate.getName());
		System.out.println(updatedDoctor);
	}

	@Test
	void deleteDoctor() {
		// Given
		Doctor doctor = Doctor.builder().id(1).name("Kabilan").specialization("Cardiology").build();

		// When
		when(doctorRepository.findById(doctor.getId())).thenReturn(Optional.of(doctor));

		// Then
		doctorRepository.deleteById(doctor.getId());
		verify(doctorRepository, times(1)).deleteById(doctor.getId());
	}
}
