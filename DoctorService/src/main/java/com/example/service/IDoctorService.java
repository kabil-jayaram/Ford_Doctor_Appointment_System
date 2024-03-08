package com.example.service;

import com.example.dto.DoctorDto;
import com.example.entity.Doctor;

import java.util.List;


public interface IDoctorService {

    public Doctor addDoctor(Doctor doctor);

    public Doctor updateDoctor(Doctor doctor);

    public List<DoctorDto> getAllDoctor();

    public DoctorDto getDoctoryById(int id);

    public List<DoctorDto> getDoctorBySpecialization(String specialization);

    public DoctorDto getDoctorByIdAndSpecialization(int id, String specialization);

    public Boolean deleteDoctor(int id);

}
