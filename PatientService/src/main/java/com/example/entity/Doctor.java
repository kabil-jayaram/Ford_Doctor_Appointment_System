package com.example.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String specialization;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Appointment> appointmentsList;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<TimeSlot> timeSlot;
}
