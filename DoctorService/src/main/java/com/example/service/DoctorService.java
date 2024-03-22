package com.example.service;

import com.example.dto.DoctorDto;
import com.example.entity.Doctor;
import com.example.entity.TimeSlot;
import com.example.mapper.DoctorMapper;
import com.example.repository.DoctorRepository;
import com.example.repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService implements IDoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Override
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        Doctor existingDoctor = doctorRepository.findById(doctor.getId()).get();

        existingDoctor.setName(doctor.getName());
        existingDoctor.setSpecialization(doctor.getSpecialization());

        List<TimeSlot> updatedTimeSlots = existingDoctor.getTimeSlot();
        for (TimeSlot timeSlot : updatedTimeSlots) {
            Optional<TimeSlot> optionalExistingTimeSlot = timeSlotRepository.findById(timeSlot.getId());
            if(optionalExistingTimeSlot.isPresent()) {
                TimeSlot existingTimeSlot = optionalExistingTimeSlot.get();
                existingTimeSlot.setStartTime(timeSlot.getStartTime());
                existingTimeSlot.setEndTime(timeSlot.getEndTime());
                existingTimeSlot.setAvailable(timeSlot.isAvailable());
            }
        }

        return doctorRepository.save(existingDoctor);
    }

    @Override
    public List<DoctorDto> getAllDoctor() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream().map(DoctorMapper::doctortoDoctorDto).toList();
    }

    @Override
    public DoctorDto getDoctoryById(int id) {
        return DoctorMapper.doctortoDoctorDto(doctorRepository.findById(id).get());
    }

    @Override
    public List<DoctorDto> getDoctorBySpecialization(String specialization) {
        List<Doctor> doctors = doctorRepository.findAllBySpecialization(specialization);
        return doctors.stream().map(DoctorMapper::doctortoDoctorDto).toList();
    }

    @Override
    public DoctorDto getDoctorByIdAndSpecialization(int id, String specialization) {
        return DoctorMapper.doctortoDoctorDto(doctorRepository.findByIdAndSpecialization(id, specialization));
    }

    @Override
    public Boolean deleteDoctor(int id) {
        doctorRepository.deleteById(id);
        return true;
    }
}
