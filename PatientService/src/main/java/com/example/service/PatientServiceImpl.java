package com.example.service;

import com.example.entity.Address;
import com.example.entity.Patient;
import com.example.repository.AddressRepository;
import com.example.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements IPatientService{
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private AddressRepository addressRepository;


    @Override
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);

    }

    @Override
    public Patient updatePatient(Patient patient) {
        Patient existingPatient = patientRepository.findById(patient.getId()).get();
        existingPatient.setName(patient.getName());
        existingPatient.setAddress(patient.getAddress());
        existingPatient.setAge(patient.getAge());
        existingPatient.setDescription(patient.getDescription());
        existingPatient.setDiagnosis(patient.getDiagnosis());
        return patientRepository.save(existingPatient);
    }

    @Override
    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(int id) {
        return patientRepository.findById(id).get();
    }

    @Override
    public  Patient getPatientByCity(String city) {
        return patientRepository.findByAddressCity(city);
    }

    @Override
    public Patient getDoctorByIdAndName(int id, String name) {
        return patientRepository.findByIdAndName(id,name);
    }

    @Override
    public Patient getPatientByAge(int age) {
        return patientRepository.findByAge(age);
    }

    @Override
    public Patient getPatientByDiagnosis(String diagnosis) {
        return patientRepository.findByDiagnosis(diagnosis);
    }

    @Override
    public Boolean deletePatient(int id) {
        patientRepository.deleteById(id);
        return true;
    }
}
