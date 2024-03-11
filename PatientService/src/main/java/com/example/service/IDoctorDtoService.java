package com.example.service;

import com.example.dto.DoctorDto;

import java.util.List;

public interface IDoctorDtoService {
    public List<DoctorDto> getDoctors();
    public List<DoctorDto> getDoctorsBySpecialization(String specialization);
    public DoctorDto getDoctorById(int doctorId);
    public DoctorDto getDoctorByIdAndSpecialization(int doctorId,String specialization);
}
