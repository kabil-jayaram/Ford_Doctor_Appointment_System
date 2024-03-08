package com.example.mapper;

import com.example.Dto.PatientDto;
import com.example.entity.Patient;

public class PatientMapper {

    public static PatientDto patientToPatientDto(Patient patient) {
        return new PatientDto(patient.getId(), patient.getName(), patient.getAge());
    }

    public static Patient patientDtoToPatient(PatientDto patientDto) {
        return new Patient(patientDto.getId(), patientDto.getName(), patientDto.getAge(), null);
    }

}
