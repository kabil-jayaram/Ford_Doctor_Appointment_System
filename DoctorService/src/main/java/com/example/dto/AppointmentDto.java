package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentDto {
    private int id;
    private int patientId;
    private int doctorId;
    private int timeSlot;
    private LocalDate localDate;
    private String description;
    private String  diagnosis;
    private String status;
}
