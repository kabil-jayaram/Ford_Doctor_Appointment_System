package com.example.entity;

import javax.persistence.*;

import com.example.dto.AppointmentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
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
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<TimeSlot> timeSlot;
}
