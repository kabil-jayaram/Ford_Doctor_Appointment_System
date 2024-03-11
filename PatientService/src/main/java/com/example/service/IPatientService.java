package com.example.service;

import com.example.entity.Address;
import com.example.entity.Patient;

import java.util.List;

public interface IPatientService {

    public Patient addPatient(Patient patient);
    public Patient updatePatient(Patient patient);
    public List<Patient> getAllPatient();
    public Patient getPatientById(int id);
    public Patient getPatientByCity(String city);
    public Patient getDoctorByIdAndName(int id,String name);
    public Patient getPatientByAge(int age);


 //   public AppointmentDto requestAppointment(int doctorId,TimeSlotDto timeSlot);
 //   public boolean cancelAppointment(int appointmentId);

    
    public Boolean deletePatient(int id);

}
