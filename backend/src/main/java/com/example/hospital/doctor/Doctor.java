package com.example.hospital.doctor;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long doctorId;

    @NotBlank
    @Column(nullable = false)
    private String specialization;

    @Min(0)
    @Column(name = "max_daily_patients", nullable = false)
    private Integer maxDailyPatients;

    @Min(0)
    @Column(name = "current_appointments", nullable = false)
    private Integer currentAppointments = 0;

    public Doctor() {
    }

    public Doctor(String specialization, Integer maxDailyPatients) {
        this.specialization = specialization;
        this.maxDailyPatients = maxDailyPatients;
        this.currentAppointments = 0;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

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

    public Integer getCurrentAppointments() {
        return currentAppointments;
    }

    public void setCurrentAppointments(Integer currentAppointments) {
        this.currentAppointments = currentAppointments;
    }
}

