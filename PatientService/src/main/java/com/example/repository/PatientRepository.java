package com.example.repository;

import com.example.entity.Address;
import com.example.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {

     Patient findByAddressCity(String city);
    Patient findByIdAndName(int id, String name);

    Patient findByAge(int age);

}
