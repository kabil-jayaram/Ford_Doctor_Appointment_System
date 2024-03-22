package com.example;

import com.example.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DoctorServiceApplication {
	@Autowired
	DoctorService doctorService;

	public static void main(String[] args) {
		SpringApplication.run(com.example.DoctorServiceApplication.class, args);
	}
}
