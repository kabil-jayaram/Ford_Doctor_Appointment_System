package com.example.service;

import com.example.entity.Appointment;
import com.example.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService implements IAppointmentService{

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Appointment addAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment getAppointmentById(int id) {
        return appointmentRepository.findById(id).get();
    }

    @Override
    public List<Appointment> getAppointmentByPatientId(int patientId) {
        return appointmentRepository.findAllByPatientId(patientId);
    }

    @Override
    public List<Appointment> getAppointmentByPatientIdAndStatus(int patientId, String status) {
        return appointmentRepository.findAllByPatientIdAndStatus(patientId, status);
    }

    @Override
    public List<Appointment> getAppointmentByDoctorId(int doctorId) {
        return appointmentRepository.findAllByDoctorId(doctorId);
    }

    @Override
    public List<Appointment> getAppointmentByDoctorIdAndStatus(int doctorId, String status) {
        return appointmentRepository.findAllByDoctorIdAndStatus(doctorId, status);
    }

    @Override
    public List<Appointment> getAppointmentByStatus(String status) {
        return appointmentRepository.findAllByStatus(status);
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Boolean deleteAppointment(int id) {
        appointmentRepository.deleteById(id);
        return true;
    }
}
