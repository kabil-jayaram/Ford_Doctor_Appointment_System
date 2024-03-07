package DoctorService.src.main.java.com.example.entity;

import java.time.LocalDate;

public class Appointment {
    private int id;
    private int patientId;
    private int doctorId;
    private int timeSlot;
    private LocalDate localDate;
    private String status;
}
