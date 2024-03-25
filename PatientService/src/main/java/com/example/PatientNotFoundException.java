package com.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientNotFoundException extends RuntimeException {
    private String message;

    public PatientNotFoundException(String message) {
        this.message = message;
    }
}
