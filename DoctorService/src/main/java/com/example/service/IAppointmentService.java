package com.example.service;

import com.example.entity.Appointment;

import java.util.List;

public interface IAppointmentService {

    public Appointment addAppointment(Appointment appointment);
    public Appointment getAppointmentById(int id);
    public Appointment getAppointmentByPatientId(int patientId);
    public List<Appointment> getAppointmentByDoctorId(int doctorId);
    public Appointment updateAppointment(Appointment appointment);
    public Boolean deleteAppointment(int id);
    public List<Appointment> getAppointmentByStatus(String status);
}
