package com.example.service;

import com.example.dto.DoctorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorDtoService implements IDoctorDtoService{

    @Autowired
    private APIClient apiClient;
    @Override
    public List<DoctorDto> getDoctors() {

        return apiClient.getDoctors();
    }

    @Override
    public List<DoctorDto> getDoctorsBySpecialization(String specialization) {
        return apiClient.getDoctorsBySpecialization(specialization);
    }

    @Override
    public DoctorDto getDoctorById(int doctorId) {
        return apiClient.getDoctorById(doctorId);
    }

    @Override
    public DoctorDto getDoctorByIdAndSpecialization(int doctorId, String specialization) {
        return apiClient.getDoctorByIdAndSpecialization(doctorId,specialization);
    }

}
