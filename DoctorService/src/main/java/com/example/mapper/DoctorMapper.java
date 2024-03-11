package com.example.mapper;

import com.example.dto.DoctorDto;
import com.example.entity.Doctor;

public class DoctorMapper {
    public static Doctor doctorDtoToDoctor(DoctorDto doctorDto) {
        return new Doctor(doctorDto.getId(), doctorDto.getName(), doctorDto.getSpecialization(),
                null, doctorDto.getTimeSlot());
    }

    public static DoctorDto doctortoDoctorDto(Doctor doctor) {
        return new DoctorDto(doctor.getId(), doctor.getName(), doctor.getSpecialization(),
                doctor.getTimeSlot());
    }
}
