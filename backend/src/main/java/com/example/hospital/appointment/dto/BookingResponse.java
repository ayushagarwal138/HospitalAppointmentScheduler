package com.example.hospital.appointment.dto;

public class BookingResponse {

    private String message;
    private Long doctorId;
    private String specialization;
    private Integer currentAppointments;
    private Integer maxDailyPatients;

    public BookingResponse(String message, Long doctorId, String specialization,
                           Integer currentAppointments, Integer maxDailyPatients) {
        this.message = message;
        this.doctorId = doctorId;
        this.specialization = specialization;
        this.currentAppointments = currentAppointments;
        this.maxDailyPatients = maxDailyPatients;
    }

    public String getMessage() {
        return message;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public Integer getCurrentAppointments() {
        return currentAppointments;
    }

    public Integer getMaxDailyPatients() {
        return maxDailyPatients;
    }
}

