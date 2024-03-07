package com.example.repository;

import com.example.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    Doctor findBySpecialization(String specialization);

    Doctor findByIdAndSpecialization(int id, String specialization);
}
