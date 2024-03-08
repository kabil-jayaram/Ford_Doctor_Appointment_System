package com.example.service;

import com.example.entity.Patient;

import java.util.List;

public interface IPatientService {

    public Patient addPatient(Patient patient);

    public Patient updatePatient(Patient patient);

    public List<Patient> getAllPatient();

    public Patient getPatientById(int id);

    public Patient getPatientByCity(String city);

    public Patient getPatientByIdAndName(int id, String name);

    public Patient getPatientByAge(int age);

    public Patient getPatientByDiagnosis(String diagnosis);

    public Boolean deletePatient(int id);

}
