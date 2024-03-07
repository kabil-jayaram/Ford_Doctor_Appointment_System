package com.example.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String specialization;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Appointment> appointmentsList;
    private Map<Integer,Boolean> timeSlot;
}
