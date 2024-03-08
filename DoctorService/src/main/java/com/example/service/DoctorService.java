package com.example.service;

import com.example.entity.Doctor;
import com.example.entity.TimeSlot;
import com.example.repository.DoctorRepository;
import com.example.repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DoctorService implements IDoctorService{

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
            TimeSlot existingTimeSlot = timeSlotRepository.findById(timeSlot.getId()).get();
            existingTimeSlot.setStartTime(timeSlot.getStartTime());
            existingTimeSlot.setEndTime(timeSlot.getEndTime());
            existingTimeSlot.setAvailable(timeSlot.isAvailable());
        }

        return doctorRepository.save(existingDoctor);
    }

    @Override
    public List<Doctor> getAllDoctor() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctoryById(int id) {
        return doctorRepository.findById(id).get();
    }

    @Override
    public Doctor getDoctorBySpecialization(String specialization) {

        return doctorRepository.findBySpecialization(specialization);
    }

    @Override
    public Doctor getDoctorByIdAndSpecialization(int id, String specialization) {
        return doctorRepository.findByIdAndSpecialization(id,specialization);
    }

    @Override
    public Boolean deleteDoctor(int id) {
        doctorRepository.deleteById(id);
        return true;
    }
}
