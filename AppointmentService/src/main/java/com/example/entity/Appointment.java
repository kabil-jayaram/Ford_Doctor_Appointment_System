package com.example.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    private int id;
    private int patientId;
    private int doctorId;
    private int timeSlot;
    private LocalDate localDate;
    private String status;
}
