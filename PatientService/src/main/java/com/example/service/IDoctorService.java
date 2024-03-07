package com.example.service;

import com.example.entity.Doctor;

import java.util.List;


public interface IDoctorService {

   public Doctor addDoctor(Doctor doctor);
   public Doctor updateDoctor(Doctor doctor);
   public List<Doctor> getAllDoctor();
   public Doctor getDoctoryById(int id);
   public Doctor getDoctorBySpecialization(String specialization);
   public Doctor getDoctorByIdAndSpecialization(int id,String specialization);

   public Boolean deleteDoctor(int id);

}
