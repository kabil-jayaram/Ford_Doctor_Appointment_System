package com.example.repository;

import com.example.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> findAllByStatus(String status);

    List<Appointment> findAllByPatientId(int patientId);

    List<Appointment> findAllByDoctorId(int doctorId);

    List<Appointment> findAllByPatientIdAndStatus(int patientId, String status);

    List<Appointment> findAllByDoctorIdAndStatus(int doctorId, String status);
}
