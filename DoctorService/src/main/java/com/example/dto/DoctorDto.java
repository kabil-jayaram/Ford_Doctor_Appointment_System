package com.example.dto;

import com.example.entity.TimeSlot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorDto {
    private int id;
    private String name;
    private String specialization;
    private List<TimeSlot> timeSlot;
}
