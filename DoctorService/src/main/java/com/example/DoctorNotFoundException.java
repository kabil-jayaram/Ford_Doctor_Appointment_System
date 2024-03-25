package com.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorNotFoundException extends RuntimeException {
    private String message;

    public DoctorNotFoundException(String message) {
        this.message = message;
    }
}
