package com.example.service;

import com.example.entity.Appointment;

import java.util.List;

public interface IAppointmentService {

    public Appointment addAppointment(Appointment appointment);
    public Appointment getAppointmentById(int id);
    public List<Appointment> getAllAppointments();
    public List<Appointment> getAppointmentByPatientId(int patientId);
    public List<Appointment> getAppointmentByPatientIdAndStatus(int patientId, String status);
    public List<Appointment> getAppointmentByDoctorId(int doctorId);
    public List<Appointment> getAppointmentByDoctorIdAndStatus(int doctorId, String status);
    public Appointment updateAppointment(Appointment appointment);
    public Boolean deleteAppointment(int id);
    public List<Appointment> getAppointmentByStatus(String status);
}
