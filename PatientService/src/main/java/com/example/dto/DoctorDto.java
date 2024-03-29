package com.example.dto;

import com.example.entity.TimeSlot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class  DoctorDto {
    private int id;
    private String name;
    private String specialization;
    private List<TimeSlot> timeSlot;
}
