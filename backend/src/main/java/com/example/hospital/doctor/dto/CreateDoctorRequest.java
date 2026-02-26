package com.example.hospital.doctor.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CreateDoctorRequest {

    @NotBlank
    private String specialization;

    @Min(0)
    private Integer maxDailyPatients;

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Integer getMaxDailyPatients() {
        return maxDailyPatients;
    }

    public void setMaxDailyPatients(Integer maxDailyPatients) {
        this.maxDailyPatients = maxDailyPatients;
    }
}

