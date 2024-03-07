package DoctorService.src.main.java.com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto {
    private int id;
    private String name;
    private String specialization;
    private Map<Integer,Boolean> timeSlot;
}
