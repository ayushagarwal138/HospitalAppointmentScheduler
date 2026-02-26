package com.example.hospital.doctor.dto;

public class DoctorResponse {

    private Long doctorId;
    private String specialization;
    private Integer maxDailyPatients;
    private Integer currentAppointments;

    public DoctorResponse(Long doctorId, String specialization,
                          Integer maxDailyPatients, Integer currentAppointments) {
        this.doctorId = doctorId;
        this.specialization = specialization;
        this.maxDailyPatients = maxDailyPatients;
        this.currentAppointments = currentAppointments;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public Integer getMaxDailyPatients() {
        return maxDailyPatients;
    }

    public Integer getCurrentAppointments() {
        return currentAppointments;
    }
}

