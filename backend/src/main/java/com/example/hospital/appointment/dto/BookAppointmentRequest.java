package com.example.hospital.appointment.dto;

import jakarta.validation.constraints.NotBlank;

public class BookAppointmentRequest {

    @NotBlank
    private String specialization;

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}

